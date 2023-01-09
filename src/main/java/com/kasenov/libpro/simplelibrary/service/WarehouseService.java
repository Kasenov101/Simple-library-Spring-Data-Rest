package com.kasenov.libpro.simplelibrary.service;

import com.kasenov.libpro.simplelibrary.model.Warehouse;
import com.kasenov.libpro.simplelibrary.repository.WarehouseRepository;
import org.springframework.stereotype.Service;

@Service
public class WarehouseService extends AbstractService<Warehouse,
        WarehouseRepository>{
    protected WarehouseService(WarehouseRepository repository) {
        super(repository);
    }
}
