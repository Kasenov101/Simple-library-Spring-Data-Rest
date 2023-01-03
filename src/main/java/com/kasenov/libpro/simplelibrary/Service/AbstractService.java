package com.kasenov.libpro.simplelibrary.Service;

import com.kasenov.libpro.simplelibrary.Entity.AbstractEntity;
import com.kasenov.libpro.simplelibrary.ExceptionHandler.CannotRemoveException;
import com.kasenov.libpro.simplelibrary.ExceptionHandler.CannotSaveException;
import com.kasenov.libpro.simplelibrary.ExceptionHandler.NotFoundException;
import com.kasenov.libpro.simplelibrary.Repository.CommonRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public abstract class AbstractService<E extends AbstractEntity, R extends CommonRepository<E>>
        implements CommonService<E> {

    private final R repository;

    protected AbstractService(R repository) {
        this.repository = repository;
    }

    public List<E> getAll() throws NotFoundException {
        List<E> list = this.repository.findAll();
        if (list.isEmpty())
            throw new NotFoundException("The list of objects is empty");
        return list;
        }

    public E getById(long id) throws NotFoundException {
        return this.repository.findById(id).orElseThrow(() ->
            new NotFoundException(id));
    }

    public ResponseEntity<E> save(E entity) throws CannotSaveException {
        if (this.repository.findById(entity.getId()).isPresent())
            throw new CannotSaveException(String.format("Object with id: %d already exist", entity.getId()));

        try {
                this.repository.saveAndFlush(entity);
                return new ResponseEntity<>(entity, HttpStatus.CREATED);
            } catch (Exception e) {
                throw new CannotSaveException(e.getMessage());
            }
        }

    public ResponseEntity<E> update(E entity) throws NotFoundException, CannotSaveException {
        if (this.repository.findById(entity.getId()).isEmpty())
            throw new NotFoundException(entity.getId());

        try {
                return new ResponseEntity<E>(repository.saveAndFlush(entity), HttpStatus.OK);
            } catch (Exception e) {
                throw new CannotSaveException(e.getMessage());
        }
    }

    public ResponseEntity<Long> removeById(long id) throws NotFoundException, CannotRemoveException {
        if (this.repository.findById(id).isEmpty())
            throw new NotFoundException(id);

        try {
                this.repository.deleteById(id);
                return new ResponseEntity<>(id, HttpStatus.OK);
            } catch (Exception e) {
                throw new CannotRemoveException(e.getMessage());
        }
    }
}
