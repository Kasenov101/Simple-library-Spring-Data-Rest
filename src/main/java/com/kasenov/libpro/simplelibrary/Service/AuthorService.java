package com.kasenov.libpro.simplelibrary.Service;

import com.kasenov.libpro.simplelibrary.Entity.Author;
import com.kasenov.libpro.simplelibrary.Repository.AuthorRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthorService extends AbstractService<Author, AuthorRepository> {
    protected AuthorService(AuthorRepository repository) {
        super(repository);
    }
}
