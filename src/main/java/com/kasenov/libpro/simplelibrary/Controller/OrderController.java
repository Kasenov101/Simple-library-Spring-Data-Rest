package com.kasenov.libpro.simplelibrary.Controller;

import com.kasenov.libpro.simplelibrary.Entity.Order;
import com.kasenov.libpro.simplelibrary.Repository.OrderRepository;
import com.kasenov.libpro.simplelibrary.Service.OrderService;
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
