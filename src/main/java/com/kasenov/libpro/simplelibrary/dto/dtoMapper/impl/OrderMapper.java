package com.kasenov.libpro.simplelibrary.dto.dtoMapper.impl;

import com.kasenov.libpro.simplelibrary.dto.dtoImpl.Book;
import com.kasenov.libpro.simplelibrary.dto.dtoImpl.Client;
import com.kasenov.libpro.simplelibrary.dto.dtoImpl.Order;
import com.kasenov.libpro.simplelibrary.dto.dtoMapper.ComplexDtoMapper;
import com.kasenov.libpro.simplelibrary.model.EntityImpl.BookEntity;
import com.kasenov.libpro.simplelibrary.model.EntityImpl.ClientEntity;
import com.kasenov.libpro.simplelibrary.model.EntityImpl.OrderEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderMapper implements ComplexDtoMapper<OrderEntity, Order> {
    @Override
    public Order toDto(OrderEntity entity) {
        Order order = SimpleDtoMapper.map(entity, Order.class);
        order.setBooks((List<Book>) SimpleDtoMapper.mapAll(entity.getBookEntities(), Book.class));
        order.setClient(SimpleDtoMapper.map(entity.getClientEntity(), Client.class));
        return order;
    }

    @Override
    public OrderEntity toEntity(Order dto) {
        OrderEntity order = SimpleDtoMapper.map(dto, OrderEntity.class);
        order.setBookEntities((List<BookEntity>) SimpleDtoMapper.mapAll(dto.getBooks(), BookEntity.class));
        order.setClientEntity(SimpleDtoMapper.map(dto.getClient(), ClientEntity.class));
        return order;
    }
}
