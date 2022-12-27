package com.kasenov.libpro.simplelibrary.Service;

import com.kasenov.libpro.simplelibrary.Entity.Author;
import com.kasenov.libpro.simplelibrary.ExceptionHandler.CannotRemoveException;
import com.kasenov.libpro.simplelibrary.ExceptionHandler.CannotSaveException;
import com.kasenov.libpro.simplelibrary.ExceptionHandler.NotFoundException;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Objects;

public interface AuthorService {

    List<Author> getAll() throws NotFoundException;

    Author getById(long id) throws NotFoundException;

    ResponseEntity<Objects> saveAuthorOrUpdate(Author author) throws CannotSaveException;

    ResponseEntity<Objects> removeAuthor(long id) throws NotFoundException, CannotRemoveException;
}
