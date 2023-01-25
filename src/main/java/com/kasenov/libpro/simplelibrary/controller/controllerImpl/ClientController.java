package com.kasenov.libpro.simplelibrary.controller.controllerImpl;

import com.kasenov.libpro.simplelibrary.controller.AbstractController;
import com.kasenov.libpro.simplelibrary.dto.dtoImpl.Client;
import com.kasenov.libpro.simplelibrary.model.EntityImpl.ClientEntity;
import com.kasenov.libpro.simplelibrary.repository.ClientRepository;
import com.kasenov.libpro.simplelibrary.service.ServiceImpl.ClientService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/clients")
public class ClientController extends AbstractController<ClientEntity, ClientRepository,
        ClientService, Client> {

    public ClientController(ClientService service, Client dto, ClientEntity entity) {
        super(service, dto, entity);
    }
}
