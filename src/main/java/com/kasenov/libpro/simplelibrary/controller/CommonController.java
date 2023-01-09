package com.kasenov.libpro.simplelibrary.controller;

import com.kasenov.libpro.simplelibrary.model.AbstractEntity;
import com.kasenov.libpro.simplelibrary.exceptionHandler.CannotRemoveException;
import com.kasenov.libpro.simplelibrary.exceptionHandler.CannotSaveException;
import com.kasenov.libpro.simplelibrary.exceptionHandler.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface CommonController<E extends AbstractEntity>{

    @GetMapping
    public List<E> getAll() throws NotFoundException;

    @GetMapping("/{id}")
    public E getById(@PathVariable("id") long id) throws NotFoundException;

    @PostMapping
    public ResponseEntity<E> save(@RequestBody E entity) throws CannotSaveException, NotFoundException;

    @PutMapping
    public ResponseEntity<E> update(@RequestBody E entity) throws CannotSaveException, NotFoundException;

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> removeById(@PathVariable("id") long id) throws CannotRemoveException, NotFoundException, CannotSaveException;
}
