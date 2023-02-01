package com.kasenov.libpro.simplelibrary.controller.controllerImpl;

import com.kasenov.libpro.simplelibrary.controller.CommonController;
import com.kasenov.libpro.simplelibrary.dto.dtoImpl.Order;
import com.kasenov.libpro.simplelibrary.exceptionHandler.CannotRemoveException;
import com.kasenov.libpro.simplelibrary.exceptionHandler.CannotSaveException;
import com.kasenov.libpro.simplelibrary.exceptionHandler.NotFoundException;
import com.kasenov.libpro.simplelibrary.model.EntityImpl.OrderEntity;
import com.kasenov.libpro.simplelibrary.service.ServiceImpl.OrderService;
import com.kasenov.libpro.simplelibrary.dto.dtoMapper.impl.OrderMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController implements CommonController<Order> {

    private final OrderMapper MAPPER;
    private final OrderService SERVICE;

    public OrderController(OrderMapper MAPPER, OrderService SERVICE) {
        this.MAPPER = MAPPER;
        this.SERVICE = SERVICE;
    }

    @Override
    public List<Order> getAll() throws NotFoundException {
        return SERVICE.getAll().stream()
                .map(MAPPER::toDto)
                .toList();
    }

    @Override
    public Order getById(long id) throws NotFoundException {
        return MAPPER.toDto(SERVICE.getById(id));
    }

    @Override
    public ResponseEntity<Order> save(Order dto) throws CannotSaveException {
        OrderEntity entity = MAPPER.toEntity(dto);
        return new ResponseEntity<>(MAPPER.toDto(SERVICE.save(entity)),HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Order> update(Order dto) throws CannotSaveException, NotFoundException {
        OrderEntity entity = MAPPER.toEntity(dto);
        SERVICE.update(entity);
        return new ResponseEntity<>(MAPPER.toDto(entity),HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Long> removeById(long id) throws CannotRemoveException, NotFoundException,
                                                            CannotSaveException {
        return new ResponseEntity<>(SERVICE.removeById(id),HttpStatus.OK);
    }

    @PostMapping("/{id}")
    public ResponseEntity<Long> completeOrder(@PathVariable("id") long id) throws CannotRemoveException,
                                                                                    NotFoundException {
        return SERVICE.completeTheOrder(id);
    }
}
