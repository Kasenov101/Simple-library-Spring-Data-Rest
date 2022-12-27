package com.kasenov.libpro.simplelibrary.Controller;


import com.kasenov.libpro.simplelibrary.Entity.Book;
import com.kasenov.libpro.simplelibrary.ExceptionHandler.CannotRemoveException;
import com.kasenov.libpro.simplelibrary.ExceptionHandler.CannotSaveException;
import com.kasenov.libpro.simplelibrary.ExceptionHandler.NotFoundException;
import com.kasenov.libpro.simplelibrary.Service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> getAll() throws NotFoundException {
        return bookService.getAll();
    }

    @GetMapping("/{id}")
    public Book getById(@PathVariable("id") long id) throws NotFoundException {
        return bookService.getById(id);
    }

    @PatchMapping
    public void saveBook(@RequestBody Book book) throws CannotSaveException {
        bookService.saveOrUpdateBook(book);
    }

    @DeleteMapping("/{id}")
    public void removeBook(@PathVariable("id") long id) throws CannotRemoveException, NotFoundException {
        bookService.removeBook(id);
    }
}
