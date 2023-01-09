package com.kasenov.libpro.simplelibrary.controller;

import com.kasenov.libpro.simplelibrary.model.Client;
import com.kasenov.libpro.simplelibrary.repository.ClientRepository;
import com.kasenov.libpro.simplelibrary.service.ClientService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/clients")
public class ClientController extends AbstractController<Client, ClientRepository,
        ClientService>{

    public ClientController(ClientService service) {
        super(service);
    }
}
