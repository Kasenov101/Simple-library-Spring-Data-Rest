package com.kasenov.libpro.simplelibrary.service.ServiceImpl;

import com.kasenov.libpro.simplelibrary.model.EntityImpl.ClientEntity;
import com.kasenov.libpro.simplelibrary.repository.ClientRepository;
import com.kasenov.libpro.simplelibrary.service.AbstractService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ClientService extends AbstractService<ClientEntity, ClientRepository> {

    protected ClientService(ClientRepository repository) {
        super(repository);
    }
}
