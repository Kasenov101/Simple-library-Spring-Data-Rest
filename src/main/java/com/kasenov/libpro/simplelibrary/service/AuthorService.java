package com.kasenov.libpro.simplelibrary.service;

import com.kasenov.libpro.simplelibrary.model.AuthorEntity;
import com.kasenov.libpro.simplelibrary.repository.AuthorRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthorService extends AbstractService<AuthorEntity,AuthorRepository> {

    protected AuthorService(AuthorRepository repository) {
        super(repository);
    }
}
