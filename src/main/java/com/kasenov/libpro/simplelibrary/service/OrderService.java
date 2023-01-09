package com.kasenov.libpro.simplelibrary.service;

import com.kasenov.libpro.simplelibrary.model.Book;
import com.kasenov.libpro.simplelibrary.model.Order;
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
public class OrderService extends AbstractService<Order, OrderRepository>{

    private final BookService bookService;
    private final OrderRepository orderRepository;
        protected OrderService(OrderRepository repository, BookService bookService) {
        super(repository);
        this.orderRepository = repository;
        this.bookService = bookService;
    }

    @Override
    public ResponseEntity<Order> save(Order entity) throws CannotSaveException, NotFoundException {
        entity.setDateOfReceiving(LocalDate.now());
            List<Book> books = entity.getBooks().stream()
                .map((b1) -> {
                    try {
                        return bookService.getById(b1.getId());
                    }catch (NotFoundException e) {
                        throw new RuntimeException(e.getMessage());}})
                .filter(b1 -> b1.getWarehouse().getQuantity() > 0)
                .peek(b1 -> b1.getWarehouse().setQuantity(b1.getWarehouse().getQuantity() -1))
                    .toList();

            if(books.isEmpty()) throw new NotFoundException("There are no books at the moment");
            entity.setBooks(books);

         try {
             entity.setOrderIsCompleted(false);
             orderRepository.saveAndFlush(entity);
             return new ResponseEntity<>(entity, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new CannotSaveException(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<Long> removeById(long id) throws NotFoundException, CannotRemoveException, CannotSaveException {
        Order order = getById(id);
        if (order.isOrderIsCompleted()) throw new CannotRemoveException("Order is already completed");
        order.setReturnedDate(LocalDate.now());
        if (order.getReturnDate().equals(order.getReturnedDate())) {
            order.setReturnedOnTime(true);
        }

        List<Book> books = order.getBooks().stream()
                .map(book -> {
                    try {
                        return bookService.getById(book.getId());
                    } catch (NotFoundException e) {
                        throw new RuntimeException(e.getMessage());
                    }})
                .peek(book -> book.getWarehouse().setQuantity(((book.getWarehouse().getQuantity()) +1)))
                .toList();

        order.setOrderIsCompleted(true);
        orderRepository.save(order);
        return new ResponseEntity<>(order.getId(),HttpStatus.OK);
    }
}
