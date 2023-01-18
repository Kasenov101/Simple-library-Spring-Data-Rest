package com.kasenov.libpro.simplelibrary.controller;

import com.kasenov.libpro.simplelibrary.dto.Order;
import com.kasenov.libpro.simplelibrary.exceptionHandler.CannotRemoveException;
import com.kasenov.libpro.simplelibrary.exceptionHandler.CannotSaveException;
import com.kasenov.libpro.simplelibrary.exceptionHandler.NotFoundException;
import com.kasenov.libpro.simplelibrary.model.OrderEntity;
import com.kasenov.libpro.simplelibrary.service.OrderService;
import com.kasenov.libpro.simplelibrary.dto.dtoMapper.impl.OrderMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController implements CommonController<Order>{

    private final OrderMapper mapper;
    private final OrderService service;

    public OrderController(OrderMapper mapper, OrderService service) {
        this.mapper = mapper;
        this.service = service;
    }

    @Override
    public List<Order> getAll() throws NotFoundException {
        return service.getAll().stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public Order getById(long id) throws NotFoundException {
        return mapper.toDto(service.getById(id));
    }

    @Override
    public ResponseEntity<Order> save(Order dto) throws CannotSaveException, NotFoundException {
        OrderEntity entity = mapper.toEntity(dto);
        service.save(entity);
        return new ResponseEntity<>(mapper.toDto(entity),HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Order> update(Order dto) throws CannotSaveException, NotFoundException {
        OrderEntity entity = mapper.toEntity(dto);
        service.update(entity);
        return new ResponseEntity<>(mapper.toDto(entity),HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Long> removeById(long id) throws CannotRemoveException, NotFoundException,
                                                            CannotSaveException {
        return new ResponseEntity<>(service.removeById(id),HttpStatus.OK);
    }

    @PostMapping("/{id}")
    public ResponseEntity<Long> completeOrder(@PathVariable("id") long id) throws CannotRemoveException,
                                                                                    NotFoundException {
        return service.completeTheOrder(id);
    }
}
