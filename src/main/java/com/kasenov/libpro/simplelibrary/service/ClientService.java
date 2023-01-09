package com.kasenov.libpro.simplelibrary.service;

import com.kasenov.libpro.simplelibrary.model.Client;
import com.kasenov.libpro.simplelibrary.repository.ClientRepository;
import org.springframework.stereotype.Service;

@Service
public class ClientService extends AbstractService<Client, ClientRepository>{
    protected ClientService(ClientRepository repository) {
        super(repository);
    }
}
