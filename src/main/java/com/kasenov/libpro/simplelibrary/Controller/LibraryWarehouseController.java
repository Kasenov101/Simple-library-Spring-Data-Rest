package com.kasenov.libpro.simplelibrary.Controller;

import com.kasenov.libpro.simplelibrary.Entity.Warehouse;
import com.kasenov.libpro.simplelibrary.Repository.WarehouseRepository;
import com.kasenov.libpro.simplelibrary.Service.WarehouseService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/warehouse")
public class LibraryWarehouseController extends AbstractController<Warehouse,
        WarehouseRepository, WarehouseService>{

    public LibraryWarehouseController(WarehouseService service) {
        super(service);
    }
}
