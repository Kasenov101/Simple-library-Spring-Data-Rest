package com.kasenov.libpro.simplelibrary.Service;

import com.kasenov.libpro.simplelibrary.Entity.Genre;
import com.kasenov.libpro.simplelibrary.Repository.GenreRepository;
import org.springframework.stereotype.Service;

@Service
public class GenreService extends AbstractService<Genre,GenreRepository> {

    protected GenreService(GenreRepository repository) {
        super(repository);
    }
}
