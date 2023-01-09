package com.kasenov.libpro.simplelibrary.controller;

import com.kasenov.libpro.simplelibrary.model.Order;
import com.kasenov.libpro.simplelibrary.repository.OrderRepository;
import com.kasenov.libpro.simplelibrary.service.OrderService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
public class OrderController extends AbstractController<Order, OrderRepository,
        OrderService>{

    public OrderController(OrderService service) {
        super(service);
    }
}
