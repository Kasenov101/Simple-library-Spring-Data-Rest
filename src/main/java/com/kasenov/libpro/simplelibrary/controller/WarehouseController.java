package com.kasenov.libpro.simplelibrary.controller;

import com.kasenov.libpro.simplelibrary.model.Warehouse;
import com.kasenov.libpro.simplelibrary.repository.WarehouseRepository;
import com.kasenov.libpro.simplelibrary.service.WarehouseService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/warehouse")
public class WarehouseController extends AbstractController<Warehouse,
        WarehouseRepository, WarehouseService>{

    public WarehouseController(WarehouseService service) {
        super(service);
    }
}
