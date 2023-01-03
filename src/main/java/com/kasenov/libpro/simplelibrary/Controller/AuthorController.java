package com.kasenov.libpro.simplelibrary.Controller;


import com.kasenov.libpro.simplelibrary.Entity.Author;
import com.kasenov.libpro.simplelibrary.Repository.AuthorRepository;
import com.kasenov.libpro.simplelibrary.Service.AuthorService;
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
