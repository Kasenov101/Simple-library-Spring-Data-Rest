package com.kasenov.libpro.simplelibrary.controller;

import com.kasenov.libpro.simplelibrary.dto.dtoImpl.Order;
import com.kasenov.libpro.simplelibrary.dto.dtoMapper.impl.OrderMapper;
import com.kasenov.libpro.simplelibrary.dto.projection.BooksAreOver;
import com.kasenov.libpro.simplelibrary.exceptionHandler.NotFoundException;
import com.kasenov.libpro.simplelibrary.service.ReportService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    private final ReportService SERVICE;
    private final OrderMapper ORDER_MAPPER;

    public ReportController(ReportService SERVICE, OrderMapper ORDER_MAPPER) {
        this.SERVICE = SERVICE;
        this.ORDER_MAPPER = ORDER_MAPPER;
    }

    @GetMapping("/books/zero")
    public List<BooksAreOver> getAllBooksAreOver() throws NotFoundException {
        return SERVICE.findWhereQuantityAndAtClientIsZero();
    }

    @GetMapping("/orders/incomplete")
    public List<Order> getAllOrdersTimeAreOver() throws NotFoundException {
        return SERVICE.findAllReturnTimeOut().stream()
                .map(ORDER_MAPPER::toDto)
                .collect(Collectors.toList());
    }
}
