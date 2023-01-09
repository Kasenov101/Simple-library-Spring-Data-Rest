package com.kasenov.libpro.simplelibrary.controller;


import com.kasenov.libpro.simplelibrary.model.Genre;
import com.kasenov.libpro.simplelibrary.repository.GenreRepository;
import com.kasenov.libpro.simplelibrary.service.GenreService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/genres")
public class GenreController extends AbstractController<Genre,
        GenreRepository, GenreService>{

    public GenreController(GenreService service) {
        super(service);
    }
}
