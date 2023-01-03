package com.kasenov.libpro.simplelibrary.Controller;

import com.kasenov.libpro.simplelibrary.Entity.Book;
import com.kasenov.libpro.simplelibrary.ExceptionHandler.CannotSaveException;
import com.kasenov.libpro.simplelibrary.Repository.BookRepository;
import com.kasenov.libpro.simplelibrary.Service.BookService;
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
    public ResponseEntity<Book> save(Book entity) throws CannotSaveException {
        entity.getWarehouse().setBook(entity);
        return super.save(entity);
    }
}
