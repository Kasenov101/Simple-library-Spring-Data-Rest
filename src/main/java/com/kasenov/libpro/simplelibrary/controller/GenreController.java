package com.kasenov.libpro.simplelibrary.controller;

import com.kasenov.libpro.simplelibrary.dto.Genre;
import com.kasenov.libpro.simplelibrary.model.GenreEntity;
import com.kasenov.libpro.simplelibrary.repository.GenreRepository;
import com.kasenov.libpro.simplelibrary.service.GenreService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/genres")
public class GenreController extends AbstractController<GenreEntity, GenreRepository,
        GenreService, Genre>{

    public GenreController(GenreService service, Genre dto, GenreEntity entity) {
        super(service, dto, entity);
    }
}
