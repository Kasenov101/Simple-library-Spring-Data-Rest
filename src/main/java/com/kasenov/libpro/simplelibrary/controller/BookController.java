package com.kasenov.libpro.simplelibrary.controller;

import com.kasenov.libpro.simplelibrary.model.Book;
import com.kasenov.libpro.simplelibrary.exceptionHandler.CannotSaveException;
import com.kasenov.libpro.simplelibrary.exceptionHandler.NotFoundException;
import com.kasenov.libpro.simplelibrary.repository.BookRepository;
import com.kasenov.libpro.simplelibrary.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/books")
public class BookController extends AbstractController<Book, BookRepository, BookService>{
    public BookController(BookService service) {
        super(service);
    }

    @Override
    public ResponseEntity<Book> save(Book entity) throws CannotSaveException, NotFoundException {
        entity.getWarehouse().setBook(entity);
        return super.save(entity);
    }
}
