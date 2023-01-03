package com.kasenov.libpro.simplelibrary.Controller;

import com.kasenov.libpro.simplelibrary.Entity.Client;
import com.kasenov.libpro.simplelibrary.Repository.ClientRepository;
import com.kasenov.libpro.simplelibrary.Service.ClientService;
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
