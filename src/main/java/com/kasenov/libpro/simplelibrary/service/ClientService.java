package com.kasenov.libpro.simplelibrary.service;

import com.kasenov.libpro.simplelibrary.model.ClientEntity;
import com.kasenov.libpro.simplelibrary.repository.ClientRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ClientService extends AbstractService<ClientEntity, ClientRepository>{

    protected ClientService(ClientRepository repository, ModelMapper modelMapper) {
        super(repository);
    }
}
