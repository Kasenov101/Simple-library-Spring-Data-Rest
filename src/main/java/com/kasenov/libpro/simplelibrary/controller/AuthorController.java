package com.kasenov.libpro.simplelibrary.controller;


import com.kasenov.libpro.simplelibrary.model.Author;
import com.kasenov.libpro.simplelibrary.repository.AuthorRepository;
import com.kasenov.libpro.simplelibrary.service.AuthorService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/authors")
public class AuthorController extends AbstractController<Author,
        AuthorRepository, AuthorService>{

    public AuthorController(AuthorService service) {
        super(service);
    }
}
