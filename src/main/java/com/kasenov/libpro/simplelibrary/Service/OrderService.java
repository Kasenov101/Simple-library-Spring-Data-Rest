package com.kasenov.libpro.simplelibrary.Service;

import com.kasenov.libpro.simplelibrary.Entity.Book;
import com.kasenov.libpro.simplelibrary.Entity.Order;
import com.kasenov.libpro.simplelibrary.ExceptionHandler.CannotSaveException;
import com.kasenov.libpro.simplelibrary.ExceptionHandler.NotFoundException;
import com.kasenov.libpro.simplelibrary.Repository.OrderRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrderService extends AbstractService<Order, OrderRepository>{

    private BookService bookService;
    private OrderRepository orderRepository;
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
}
