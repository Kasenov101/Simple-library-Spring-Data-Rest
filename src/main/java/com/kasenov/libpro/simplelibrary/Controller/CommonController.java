package com.kasenov.libpro.simplelibrary.Controller;

import com.kasenov.libpro.simplelibrary.Entity.AbstractEntity;
import com.kasenov.libpro.simplelibrary.ExceptionHandler.CannotRemoveException;
import com.kasenov.libpro.simplelibrary.ExceptionHandler.CannotSaveException;
import com.kasenov.libpro.simplelibrary.ExceptionHandler.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface CommonController<E extends AbstractEntity>{

    @GetMapping
    public List<E> getAll() throws NotFoundException;

    @GetMapping("/{id}")
    public E getById(@PathVariable("id") long id) throws NotFoundException;

    @PutMapping
    public ResponseEntity<E> save(E entity) throws CannotSaveException;

    @PatchMapping
    public ResponseEntity<E> update(E entity) throws CannotSaveException, NotFoundException;

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> removeById(@PathVariable("id") long id) throws CannotRemoveException, NotFoundException;
}
