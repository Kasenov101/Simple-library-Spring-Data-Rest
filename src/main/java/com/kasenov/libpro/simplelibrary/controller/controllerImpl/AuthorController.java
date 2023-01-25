package com.kasenov.libpro.simplelibrary.controller.controllerImpl;

import com.kasenov.libpro.simplelibrary.controller.AbstractController;
import com.kasenov.libpro.simplelibrary.dto.dtoImpl.Author;
import com.kasenov.libpro.simplelibrary.model.EntityImpl.AuthorEntity;
import com.kasenov.libpro.simplelibrary.repository.AuthorRepository;
import com.kasenov.libpro.simplelibrary.service.ServiceImpl.AuthorService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/authors")
public class AuthorController extends AbstractController<AuthorEntity, AuthorRepository,
        AuthorService, Author> {

    public AuthorController(AuthorService service, Author dto, AuthorEntity entity) {
        super(service, dto, entity);
    }
}
