package com.kasenov.libpro.simplelibrary.Service;

import com.kasenov.libpro.simplelibrary.Entity.Warehouse;
import com.kasenov.libpro.simplelibrary.Repository.WarehouseRepository;
import org.springframework.stereotype.Service;

@Service
public class WarehouseService extends AbstractService<Warehouse,
        WarehouseRepository>{
    protected WarehouseService(WarehouseRepository repository) {
        super(repository);
    }
}
