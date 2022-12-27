package com.kasenov.libpro.simplelibrary.Controller;

import com.kasenov.libpro.simplelibrary.Entity.Author;
import com.kasenov.libpro.simplelibrary.ExceptionHandler.CannotSaveException;
import com.kasenov.libpro.simplelibrary.ExceptionHandler.NotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class Authors {

    private final AuthorService authorService;

    public Authors(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public List<Author> getAll() throws NotFoundException {
        return authorService.getAll();
    }

    @GetMapping("/id")
    public Author getById(long id) throws NotFoundException {
        return authorService.getById(id);
    }

    @PatchMapping
    public void saveAuthorOrUpdate(@RequestBody Author author) throws CannotSaveException {
        authorService.saveAuthorOrUpdate(author);
    }
}
