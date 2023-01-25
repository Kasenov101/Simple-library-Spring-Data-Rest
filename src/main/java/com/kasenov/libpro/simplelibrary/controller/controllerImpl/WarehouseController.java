package com.kasenov.libpro.simplelibrary.controller.controllerImpl;

import com.kasenov.libpro.simplelibrary.controller.CommonController;
import com.kasenov.libpro.simplelibrary.dto.dtoImpl.Warehouse;
import com.kasenov.libpro.simplelibrary.exceptionHandler.CannotRemoveException;
import com.kasenov.libpro.simplelibrary.exceptionHandler.CannotSaveException;
import com.kasenov.libpro.simplelibrary.exceptionHandler.NotFoundException;
import com.kasenov.libpro.simplelibrary.model.EntityImpl.WarehouseEntity;
import com.kasenov.libpro.simplelibrary.service.ServiceImpl.WarehouseService;
import com.kasenov.libpro.simplelibrary.dto.dtoMapper.impl.WarehouseMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/warehouse")
public class WarehouseController implements CommonController<Warehouse> {

    private final WarehouseService SERVICE;
    private final WarehouseMapper MAPPER;

    public WarehouseController(WarehouseService SERVICE, WarehouseMapper MAPPER) {
        this.SERVICE = SERVICE;
        this.MAPPER = MAPPER;
    }

    @Override
    public List<Warehouse> getAll() throws NotFoundException {
        return SERVICE.getAll().stream()
                .map(MAPPER::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Warehouse getById(long id) throws NotFoundException {
        return MAPPER.toDto(SERVICE.getById(id));
    }

    @Override
    public ResponseEntity<Warehouse> save(Warehouse dto) throws CannotSaveException, NotFoundException {
        WarehouseEntity entity = MAPPER.toEntity(dto);
        SERVICE.save(entity);
        return new ResponseEntity<>(MAPPER.toDto(entity), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Warehouse> update(Warehouse dto) throws CannotSaveException, NotFoundException {
        WarehouseEntity entity = MAPPER.toEntity(dto);
        SERVICE.update(entity);
        return new ResponseEntity<>(MAPPER.toDto(entity), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Long> removeById(long id) throws CannotRemoveException, NotFoundException, CannotSaveException {
        return new ResponseEntity<>(SERVICE.removeById(id),HttpStatus.OK);
    }
}
