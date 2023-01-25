package com.kasenov.libpro.simplelibrary.service;

import com.kasenov.libpro.simplelibrary.model.AbstractEntity;
import com.kasenov.libpro.simplelibrary.exceptionHandler.CannotRemoveException;
import com.kasenov.libpro.simplelibrary.exceptionHandler.CannotSaveException;
import com.kasenov.libpro.simplelibrary.exceptionHandler.NotFoundException;
import com.kasenov.libpro.simplelibrary.repository.CommonRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional(readOnly = true)
public abstract class AbstractService<E extends AbstractEntity,
        R extends CommonRepository<E>> implements CommonService<E> {

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
         return this.repository.findById(id)
                 .orElseThrow(() ->
            new NotFoundException(id));
    }

    @Transactional
    public E save(E entity) throws CannotSaveException, NotFoundException {
        if (this.repository.findById(entity.getId()).isPresent())
            throw new CannotSaveException(String.format("Object with id: %d already exist", entity.getId()));

        try {
                this.repository.saveAndFlush(entity);
                return entity;
            } catch (Exception e) {
                throw new CannotSaveException(e.getMessage());
            }
        }

    @Transactional
    public E update(E entity) throws NotFoundException, CannotSaveException {
        if (this.repository.findById(entity.getId()).isEmpty())
            throw new NotFoundException(entity.getId());

        try {
            return repository.saveAndFlush(entity);
            } catch (Exception e) {
                throw new CannotSaveException(e.getMessage());
        }
    }

    @Transactional
    public Long removeById(long id) throws NotFoundException, CannotRemoveException, CannotSaveException {
        if (this.repository.findById(id).isEmpty())
            throw new NotFoundException(id);

        try {
                this.repository.deleteById(id);
                return id;
            } catch (Exception e) {
                throw new CannotRemoveException(e.getMessage());
        }
    }
}
