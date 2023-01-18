package com.kasenov.libpro.simplelibrary.service;

import com.kasenov.libpro.simplelibrary.exceptionHandler.CannotRemoveException;
import com.kasenov.libpro.simplelibrary.exceptionHandler.CannotSaveException;
import com.kasenov.libpro.simplelibrary.exceptionHandler.NotFoundException;
import com.kasenov.libpro.simplelibrary.model.AbstractEntity;

import java.util.List;

public interface CommonService<E extends AbstractEntity> {
    List<E> getAll() throws NotFoundException;

    E getById(long id) throws NotFoundException;

    E save(E entity) throws CannotSaveException, NotFoundException;

    E update(E entity) throws NotFoundException, CannotSaveException;

    Long removeById(long id) throws NotFoundException, CannotRemoveException, CannotSaveException;
}
