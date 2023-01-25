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

    protected final S SERVICE;
    private final D DTO;
    private final E ENTITY;

    public AbstractController(S SERVICE, D DTO, E ENTITY) {
        this.SERVICE = SERVICE;
        this.DTO = DTO;
        this.ENTITY = ENTITY;
    }

    @SuppressWarnings("unchecked")
    public List<D> getAll() throws NotFoundException {
        return (List<D>) SERVICE.getAll().stream()
                .map(i -> SimpleDtoMapper.map(i, DTO.getClass()))
                .collect(Collectors.toList());
    }

    @Override
    @SuppressWarnings("unchecked")
    public D getById(long id) throws NotFoundException {
        return (D) SimpleDtoMapper.map(SERVICE.getById(id), DTO.getClass());
    }

    @Override
    @SuppressWarnings("unchecked")
    public ResponseEntity<D> save(D dto) throws CannotSaveException, NotFoundException {
        E entity = (E) SimpleDtoMapper.map(dto,this.ENTITY.getClass());
        SERVICE.save(entity);
        return (ResponseEntity<D>) new ResponseEntity<>(SimpleDtoMapper.map(entity,dto.getClass()), HttpStatus.OK);
    }

    @Override
    @SuppressWarnings("unchecked")
    public ResponseEntity<D> update(D dto) throws CannotSaveException, NotFoundException {
        E entity = (E) SimpleDtoMapper.map(dto,this.ENTITY.getClass());
        SERVICE.update(entity);
        return (ResponseEntity<D>) new ResponseEntity<>(SimpleDtoMapper.map(entity,dto.getClass()), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Long> removeById(long id) throws CannotRemoveException, NotFoundException, CannotSaveException {
        return new ResponseEntity<>(SERVICE.removeById(id),HttpStatus.OK);
    }
}
