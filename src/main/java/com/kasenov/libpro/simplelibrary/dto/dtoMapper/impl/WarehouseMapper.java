package com.kasenov.libpro.simplelibrary.dto.dtoMapper.impl;

import com.kasenov.libpro.simplelibrary.dto.dtoImpl.Book;
import com.kasenov.libpro.simplelibrary.dto.dtoImpl.Warehouse;
import com.kasenov.libpro.simplelibrary.dto.dtoMapper.ComplexDtoMapper;
import com.kasenov.libpro.simplelibrary.model.EntityImpl.BookEntity;
import com.kasenov.libpro.simplelibrary.model.EntityImpl.WarehouseEntity;
import org.springframework.stereotype.Component;

@Component
public class WarehouseMapper implements ComplexDtoMapper<WarehouseEntity, Warehouse> {
    @Override
    public Warehouse toDto(WarehouseEntity entity) {
        Warehouse warehouse = SimpleDtoMapper.map(entity, Warehouse.class);
        warehouse.setBook(SimpleDtoMapper.map(entity.getBookEntity(), Book.class));
        return warehouse;
    }

    @Override
    public WarehouseEntity toEntity(Warehouse dto) {
        WarehouseEntity warehouse = SimpleDtoMapper.map(dto,WarehouseEntity.class);
        warehouse.setBookEntity(SimpleDtoMapper.map(dto.getBook(), BookEntity.class));
        return warehouse;
    }
}
