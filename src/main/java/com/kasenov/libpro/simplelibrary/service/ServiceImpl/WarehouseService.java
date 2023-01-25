package com.kasenov.libpro.simplelibrary.service.ServiceImpl;

import com.kasenov.libpro.simplelibrary.model.EntityImpl.WarehouseEntity;
import com.kasenov.libpro.simplelibrary.repository.WarehouseRepository;
import com.kasenov.libpro.simplelibrary.service.AbstractService;
import org.springframework.stereotype.Service;

@Service
public class WarehouseService extends AbstractService<WarehouseEntity,
        WarehouseRepository> {
    protected WarehouseService(WarehouseRepository repository) {
        super(repository);
    }
}
