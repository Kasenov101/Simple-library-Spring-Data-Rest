package com.kasenov.libpro.simplelibrary.service.ServiceImpl;

import com.kasenov.libpro.simplelibrary.model.EntityImpl.AuthorEntity;
import com.kasenov.libpro.simplelibrary.repository.AuthorRepository;
import com.kasenov.libpro.simplelibrary.service.AbstractService;
import org.springframework.stereotype.Service;

@Service
public class AuthorService extends AbstractService<AuthorEntity,AuthorRepository> {

    protected AuthorService(AuthorRepository repository) {
        super(repository);
    }
}
