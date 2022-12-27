package com.kasenov.libpro.simplelibrary.Service;

import com.kasenov.libpro.simplelibrary.Entity.Book;
import com.kasenov.libpro.simplelibrary.ExceptionHandler.CannotRemoveException;
import com.kasenov.libpro.simplelibrary.ExceptionHandler.CannotSaveException;
import com.kasenov.libpro.simplelibrary.ExceptionHandler.NotFoundException;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Objects;

public interface BookService{
    List<Book> getAll() throws NotFoundException;

    Book getById(long id) throws NotFoundException;

    ResponseEntity<Objects> saveOrUpdateBook(Book book) throws CannotSaveException;

    ResponseEntity<Objects> removeBook(long id) throws NotFoundException, CannotRemoveException;


}
