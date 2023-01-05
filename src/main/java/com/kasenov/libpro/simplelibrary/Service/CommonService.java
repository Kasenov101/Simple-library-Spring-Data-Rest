package com.kasenov.libpro.simplelibrary.Service;

import com.kasenov.libpro.simplelibrary.Entity.AbstractEntity;
import com.kasenov.libpro.simplelibrary.ExceptionHandler.CannotRemoveException;
import com.kasenov.libpro.simplelibrary.ExceptionHandler.CannotSaveException;
import com.kasenov.libpro.simplelibrary.ExceptionHandler.NotFoundException;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CommonService<E extends AbstractEntity> {
    List<E> getAll() throws NotFoundException;

    E getById(long id) throws NotFoundException;

    ResponseEntity<E> save(E entity) throws CannotSaveException, NotFoundException;

    ResponseEntity<E> update(E entity) throws NotFoundException, CannotSaveException;

    ResponseEntity<Long> removeById(long id) throws NotFoundException, CannotRemoveException, CannotSaveException;
}
