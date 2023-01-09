package com.kasenov.libpro.simplelibrary.controller;

import com.kasenov.libpro.simplelibrary.model.AbstractEntity;
import com.kasenov.libpro.simplelibrary.exceptionHandler.CannotRemoveException;
import com.kasenov.libpro.simplelibrary.exceptionHandler.CannotSaveException;
import com.kasenov.libpro.simplelibrary.exceptionHandler.NotFoundException;
import com.kasenov.libpro.simplelibrary.repository.CommonRepository;
import com.kasenov.libpro.simplelibrary.service.AbstractService;
import org.springframework.http.ResponseEntity;

import java.util.List;

public abstract class AbstractController<E extends AbstractEntity, R extends CommonRepository<E>,
        S extends AbstractService<E,R>> implements CommonController<E> {

    protected S service;
    public AbstractController(S service) {
        this.service = service;
    }

    @Override
    public List<E> getAll() throws NotFoundException {
        return service.getAll();
    }

    @Override
    public E getById(long id) throws NotFoundException {
        return service.getById(id);
    }

    @Override
    public ResponseEntity<E> save(E entity) throws CannotSaveException, NotFoundException {
        return service.save(entity);
    }

    @Override
    public ResponseEntity<E> update(E entity) throws CannotSaveException, NotFoundException {
        return service.update(entity);
    }

    @Override
    public ResponseEntity<Long> removeById(long id) throws CannotRemoveException, NotFoundException, CannotSaveException {
        return service.removeById(id);
    }
}
