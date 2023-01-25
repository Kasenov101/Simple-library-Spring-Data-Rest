package com.kasenov.libpro.simplelibrary.controller.controllerImpl;

import com.kasenov.libpro.simplelibrary.controller.AbstractController;
import com.kasenov.libpro.simplelibrary.dto.dtoImpl.Genre;
import com.kasenov.libpro.simplelibrary.model.EntityImpl.GenreEntity;
import com.kasenov.libpro.simplelibrary.repository.GenreRepository;
import com.kasenov.libpro.simplelibrary.service.ServiceImpl.GenreService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/genres")
public class GenreController extends AbstractController<GenreEntity, GenreRepository,
        GenreService, Genre> {

    public GenreController(GenreService service, Genre dto, GenreEntity entity) {
        super(service, dto, entity);
    }
}
