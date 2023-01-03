package com.kasenov.libpro.simplelibrary.Controller;


import com.kasenov.libpro.simplelibrary.Entity.Genre;
import com.kasenov.libpro.simplelibrary.Repository.GenreRepository;
import com.kasenov.libpro.simplelibrary.Service.GenreService;
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
