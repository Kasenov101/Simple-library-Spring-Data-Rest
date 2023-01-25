package com.kasenov.libpro.simplelibrary.service.ServiceImpl;

import com.kasenov.libpro.simplelibrary.model.EntityImpl.GenreEntity;
import com.kasenov.libpro.simplelibrary.repository.GenreRepository;
import com.kasenov.libpro.simplelibrary.service.AbstractService;
import org.springframework.stereotype.Service;

@Service
public class GenreService extends AbstractService<GenreEntity,GenreRepository> {

    protected GenreService(GenreRepository repository) {
        super(repository);
    }
}
