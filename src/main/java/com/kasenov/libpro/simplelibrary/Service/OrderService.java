package com.kasenov.libpro.simplelibrary.Service;

import com.kasenov.libpro.simplelibrary.Entity.Order;
import com.kasenov.libpro.simplelibrary.Repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService extends AbstractService<Order, OrderRepository>{
    protected OrderService(OrderRepository repository) {
        super(repository);
    }
}
