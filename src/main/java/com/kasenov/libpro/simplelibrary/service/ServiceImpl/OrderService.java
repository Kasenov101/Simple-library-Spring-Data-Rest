package com.kasenov.libpro.simplelibrary.service.ServiceImpl;

import com.kasenov.libpro.simplelibrary.model.EntityImpl.BookEntity;
import com.kasenov.libpro.simplelibrary.model.EntityImpl.OrderEntity;
import com.kasenov.libpro.simplelibrary.exceptionHandler.CannotRemoveException;
import com.kasenov.libpro.simplelibrary.exceptionHandler.CannotSaveException;
import com.kasenov.libpro.simplelibrary.exceptionHandler.NotFoundException;
import com.kasenov.libpro.simplelibrary.repository.OrderRepository;
import com.kasenov.libpro.simplelibrary.service.AbstractService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrderService extends AbstractService<OrderEntity, OrderRepository> {
    private final BookService BOOK_SERVICE;
    private final OrderRepository ORDER_REPOSITORY;
        protected OrderService(OrderRepository repository, BookService BOOK_SERVICE) {
        super(repository);
            this.ORDER_REPOSITORY = repository;
            this.BOOK_SERVICE = BOOK_SERVICE;
    }

    @Override
    public OrderEntity save(OrderEntity entity) throws CannotSaveException {
         try {
            return ORDER_REPOSITORY.saveAndFlush(checkAndPrepareToSaveOrder(entity));
        } catch (Exception e) {
            throw new CannotSaveException(e.getMessage());
        }
    }

    @Override
    public Long removeById(long id)
            throws NotFoundException, CannotRemoveException, CannotSaveException {

            if (!getById(id).isOrderIsCompleted()) throw
                    new CannotRemoveException(String.format("Order with id: " +
                            "%d is already not completed", id));
        return super.removeById(id);
    }

    @Transactional
    public ResponseEntity<Long> completeTheOrder(long id)
            throws NotFoundException, CannotRemoveException {

        OrderEntity order = getById(id);
        checkAndPrepareToCloseOrder(order);
        ORDER_REPOSITORY.save(order);
        return new ResponseEntity<>(order.getId(),HttpStatus.OK);
    }

    private OrderEntity checkAndPrepareToSaveOrder(OrderEntity order) throws CannotSaveException {
            OrderEntity entity = order;
        entity.setDateOfReceiving(LocalDate.now());
        if (entity.getReturnDate().isBefore(entity.getDateOfReceiving()) |
        entity.getReturnDate().isAfter(entity.getDateOfReceiving().plusDays(30)))
            throw new CannotSaveException("""
                    Return date must be after date of receiving and not more than
                    a 30 days""");
        List<BookEntity> books = entity.getBookEntities().stream()
                .map((b1) -> {
                    try {
                        return BOOK_SERVICE.getByIdWhereQuantityAboveZero(b1.getId());
                    }catch (NotFoundException e) {
                        throw new RuntimeException(e.getMessage());
                    }})
                .peek(b1 -> {
                    b1.getWarehouseEntity().setQuantity(b1.getWarehouseEntity().getQuantity() -1);
                    b1.getWarehouseEntity().setAtClients(b1.getWarehouseEntity().getAtClients() + 1);
                })
                .toList();
        entity.setBookEntities(books);
        System.out.println(entity.getBookEntities().get(0).getWarehouseEntity().getQuantity());
        return entity;
    }


    private void checkAndPrepareToCloseOrder(OrderEntity order) throws CannotRemoveException {
        if (order.isOrderIsCompleted())
            throw new CannotRemoveException("Order is already completed");
        order.setReturnedDate(LocalDate.now());

        if (order.getReturnDate().equals(order.getReturnedDate()) ||
                order.getReturnedDate().isBefore(order.getReturnDate())) {
            order.setReturnedOnTime(true);
        } else order.setReturnedOnTime(false);
        List<BookEntity> books = order.getBookEntities().stream()
                .peek(book -> {
                    book.getWarehouseEntity().setQuantity(((book.getWarehouseEntity().getQuantity()) +1));
                    book.getWarehouseEntity().setAtClients(book.getWarehouseEntity().getAtClients() - 1);
                })
                .toList();
        order.setOrderIsCompleted(true);
    }
}
