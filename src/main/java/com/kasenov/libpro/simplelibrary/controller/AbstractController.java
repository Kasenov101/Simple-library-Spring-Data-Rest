package com.kasenov.libpro.simplelibrary.controller;

import com.kasenov.libpro.simplelibrary.dto.AbstractDto;
import com.kasenov.libpro.simplelibrary.exceptionHandler.CannotRemoveException;
import com.kasenov.libpro.simplelibrary.exceptionHandler.CannotSaveException;
import com.kasenov.libpro.simplelibrary.exceptionHandler.NotFoundException;
import com.kasenov.libpro.simplelibrary.model.AbstractEntity;
import com.kasenov.libpro.simplelibrary.repository.CommonRepository;
import com.kasenov.libpro.simplelibrary.service.AbstractService;
import com.kasenov.libpro.simplelibrary.dto.dtoMapper.impl.SimpleDtoMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.stream.Collectors;

public class AbstractController<E extends AbstractEntity,R extends CommonRepository<E>,
        S extends AbstractService<E,R>, D extends AbstractDto>
        implements CommonController< D>{

    protected S service;
    private final D dto;
    private final E entity;

    public AbstractController(S service, D dto, E entity) {
        this.service = service;
        this.dto = dto;
        this.entity = entity;
    }

    @SuppressWarnings("unchecked")
    public List<D> getAll() throws NotFoundException {
        return (List<D>) service.getAll().stream()
                .map(i -> SimpleDtoMapper.map(i,dto.getClass()))
                .collect(Collectors.toList());
    }

    @Override
    @SuppressWarnings("unchecked")
    public D getById(long id) throws NotFoundException {
        return (D) SimpleDtoMapper.map(service.getById(id),dto.getClass());
    }

    @Override
    @SuppressWarnings("unchecked")
    public ResponseEntity<D> save(D dto) throws CannotSaveException, NotFoundException {
        E entity = (E) SimpleDtoMapper.map(dto,this.entity.getClass());
        service.save(entity);
        return (ResponseEntity<D>) new ResponseEntity<>(SimpleDtoMapper.map(entity,dto.getClass()), HttpStatus.OK);
    }

    @Override
    @SuppressWarnings("unchecked")
    public ResponseEntity<D> update(D dto) throws CannotSaveException, NotFoundException {
        E entity = (E) SimpleDtoMapper.map(dto,this.entity.getClass());
        service.update(entity);
        return (ResponseEntity<D>) new ResponseEntity<>(SimpleDtoMapper.map(entity,dto.getClass()), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Long> removeById(long id) throws CannotRemoveException, NotFoundException, CannotSaveException {
        return new ResponseEntity<>(service.removeById(id),HttpStatus.OK);
    }
}
