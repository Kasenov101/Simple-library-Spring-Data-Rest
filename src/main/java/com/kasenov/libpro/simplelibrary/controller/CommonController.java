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
    public List<D> getAll() throws NotFoundException;

    @GetMapping("/{id}")
    public D getById(@PathVariable("id") long id) throws NotFoundException;

    @PostMapping
    public ResponseEntity<D> save(@Valid @RequestBody D dto) throws CannotSaveException, NotFoundException;

    @PutMapping
    public ResponseEntity<D> update(@Valid @RequestBody D dto) throws CannotSaveException, NotFoundException;

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> removeById(@PathVariable("id") long id)
                                            throws CannotRemoveException, NotFoundException, CannotSaveException;
}
