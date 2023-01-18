package com.kasenov.libpro.simplelibrary.service;

import com.kasenov.libpro.simplelibrary.model.BookEntity;
import com.kasenov.libpro.simplelibrary.model.OrderEntity;
import com.kasenov.libpro.simplelibrary.exceptionHandler.CannotRemoveException;
import com.kasenov.libpro.simplelibrary.exceptionHandler.CannotSaveException;
import com.kasenov.libpro.simplelibrary.exceptionHandler.NotFoundException;
import com.kasenov.libpro.simplelibrary.repository.OrderRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrderService extends AbstractService<OrderEntity, OrderRepository>{


    private final BookService bookService;
    private final OrderRepository orderRepository;
        protected OrderService(OrderRepository repository, BookService bookService) {
        super(repository);
            this.orderRepository = repository;
            this.bookService = bookService;
    }

    @Override
    public OrderEntity save(OrderEntity entity) throws CannotSaveException, NotFoundException {
        entity.setDateOfReceiving(LocalDate.now());
            List<BookEntity> books = entity.getBookEntities().stream()
                .map((b1) -> {
                    try {
                        return bookService.getById(b1.getId());
                    }catch (NotFoundException e) {
                        throw new RuntimeException(e.getMessage());}})
                .filter(b1 -> b1.getWarehouseEntity().getQuantity() > 0)
                .peek(b1 -> b1.getWarehouseEntity().setQuantity(b1.getWarehouseEntity().getQuantity() -1))
                    .toList();

            if(books.isEmpty()) throw new NotFoundException("There are no books at the moment");
            entity.setBookEntities(books);

         try {
             entity.setOrderIsCompleted(false);
             return orderRepository.saveAndFlush(entity);
        } catch (Exception e) {
            throw new CannotSaveException(e.getMessage());
        }
    }

    @Override
    public Long removeById(long id)
            throws NotFoundException, CannotRemoveException, CannotSaveException {

            if (!getById(id).isOrderIsCompleted()) throw
                    new CannotRemoveException(String.format("OrderEntity with id: " +
                            "%d is already not completed", id));
        return super.removeById(id);
    }

    public ResponseEntity<Long> completeTheOrder(long id)
            throws NotFoundException, CannotRemoveException {

        OrderEntity order = getById(id);
        if (order.isOrderIsCompleted())
            throw new CannotRemoveException("OrderEntity is already completed");
        order.setReturnedDate(LocalDate.now());
        if (order.getReturnDate().equals(order.getReturnedDate())) {
            order.setReturnedOnTime(true);
        }

        List<BookEntity> books = order.getBookEntities().stream()
                .map(book -> {
                    try {
                        return bookService.getById(book.getId());
                    } catch (NotFoundException e) {
                        throw new RuntimeException(e.getMessage());
                    }})
                .peek(book -> book.getWarehouseEntity().setQuantity(((book.getWarehouseEntity().getQuantity()) +1)))
                .toList();

        order.setOrderIsCompleted(true);
        orderRepository.save(order);
        return new ResponseEntity<>(order.getId(),HttpStatus.OK);
    }
}
