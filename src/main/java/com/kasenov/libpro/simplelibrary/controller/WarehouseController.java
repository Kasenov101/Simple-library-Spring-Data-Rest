package com.kasenov.libpro.simplelibrary.controller;

import com.kasenov.libpro.simplelibrary.dto.Warehouse;
import com.kasenov.libpro.simplelibrary.exceptionHandler.CannotRemoveException;
import com.kasenov.libpro.simplelibrary.exceptionHandler.CannotSaveException;
import com.kasenov.libpro.simplelibrary.exceptionHandler.NotFoundException;
import com.kasenov.libpro.simplelibrary.model.WarehouseEntity;
import com.kasenov.libpro.simplelibrary.service.WarehouseService;
import com.kasenov.libpro.simplelibrary.dto.dtoMapper.impl.WarehouseMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/warehouse")
public class WarehouseController implements CommonController<Warehouse>{

    private final WarehouseService service;
    private final WarehouseMapper mapper;

    public WarehouseController(WarehouseService service, WarehouseMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @Override
    public List<Warehouse> getAll() throws NotFoundException {
        return service.getAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Warehouse getById(long id) throws NotFoundException {
        return mapper.toDto(service.getById(id));
    }

    @Override
    public ResponseEntity<Warehouse> save(Warehouse dto) throws CannotSaveException, NotFoundException {
        WarehouseEntity entity = mapper.toEntity(dto);
        service.save(entity);
        return new ResponseEntity<>(mapper.toDto(entity), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Warehouse> update(Warehouse dto) throws CannotSaveException, NotFoundException {
        WarehouseEntity entity = mapper.toEntity(dto);
        service.update(entity);
        return new ResponseEntity<>(mapper.toDto(entity), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Long> removeById(long id) throws CannotRemoveException, NotFoundException, CannotSaveException {
        return new ResponseEntity<>(service.removeById(id),HttpStatus.OK);
    }
}
