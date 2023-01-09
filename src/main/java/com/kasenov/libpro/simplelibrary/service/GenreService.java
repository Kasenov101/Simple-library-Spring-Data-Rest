package com.kasenov.libpro.simplelibrary.service;

import com.kasenov.libpro.simplelibrary.model.Genre;
import com.kasenov.libpro.simplelibrary.repository.GenreRepository;
import org.springframework.stereotype.Service;

@Service
public class GenreService extends AbstractService<Genre,GenreRepository> {

    protected GenreService(GenreRepository repository) {
        super(repository);
    }
}
