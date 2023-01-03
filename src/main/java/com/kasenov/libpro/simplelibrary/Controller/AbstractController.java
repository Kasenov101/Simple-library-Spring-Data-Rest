package com.kasenov.libpro.simplelibrary.Controller;

import com.kasenov.libpro.simplelibrary.Entity.AbstractEntity;
import com.kasenov.libpro.simplelibrary.ExceptionHandler.CannotRemoveException;
import com.kasenov.libpro.simplelibrary.ExceptionHandler.CannotSaveException;
import com.kasenov.libpro.simplelibrary.ExceptionHandler.NotFoundException;
import com.kasenov.libpro.simplelibrary.Repository.CommonRepository;
import com.kasenov.libpro.simplelibrary.Service.AbstractService;
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
    public ResponseEntity<E> save(E entity) throws CannotSaveException {
        return service.save(entity);
    }

    @Override
    public ResponseEntity<E> update(E entity) throws CannotSaveException, NotFoundException {
        return service.update(entity);
    }

    @Override
    public ResponseEntity<Long> removeById(long id) throws CannotRemoveException, NotFoundException {
        return service.removeById(id);
    }
}
