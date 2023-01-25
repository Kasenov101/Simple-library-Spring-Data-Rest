package com.kasenov.libpro.simplelibrary.controller;

import com.kasenov.libpro.simplelibrary.dto.AbstractDto;
import com.kasenov.libpro.simplelibrary.exceptionHandler.CannotRemoveException;
import com.kasenov.libpro.simplelibrary.exceptionHandler.CannotSaveException;
import com.kasenov.libpro.simplelibrary.exceptionHandler.NotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface CommonController<D extends AbstractDto>{

    @GetMapping
    List<D> getAll() throws NotFoundException;

    @GetMapping("/{id}")
    D getById(@PathVariable("id") long id) throws NotFoundException;

    @PostMapping
    ResponseEntity<D> save(@Valid @RequestBody D dto) throws CannotSaveException, NotFoundException;

    @PutMapping
    ResponseEntity<D> update(@Valid @RequestBody D dto) throws CannotSaveException, NotFoundException;

    @DeleteMapping("/{id}")
    ResponseEntity<Long> removeById(@PathVariable("id") long id)
                                            throws CannotRemoveException, NotFoundException, CannotSaveException;
}
