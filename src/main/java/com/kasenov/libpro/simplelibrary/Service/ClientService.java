package com.kasenov.libpro.simplelibrary.Service;

import com.kasenov.libpro.simplelibrary.Entity.Client;
import com.kasenov.libpro.simplelibrary.Repository.ClientRepository;
import org.springframework.stereotype.Service;

@Service
public class ClientService extends AbstractService<Client, ClientRepository>{
    protected ClientService(ClientRepository repository) {
        super(repository);
    }
}
