package com.kasenov.libpro.simplelibrary.Service;

import com.kasenov.libpro.simplelibrary.Entity.Author;
import com.kasenov.libpro.simplelibrary.ExceptionHandler.CannotRemoveException;
import com.kasenov.libpro.simplelibrary.ExceptionHandler.CannotSaveException;
import com.kasenov.libpro.simplelibrary.ExceptionHandler.NotFoundException;
import com.kasenov.libpro.simplelibrary.Repository.AuthorRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class AuthorServiceImpl implements AuthorService{

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> getAll() throws NotFoundException {
        List<Author> authors = authorRepository.findAll();
        if (authors.isEmpty()) throw new NotFoundException("List authors is empty");
        return authors;
    }

    @Override
    public Author getById(long id) throws NotFoundException {
        return authorRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Author", id));
    }

    @Override
    public ResponseEntity<Objects> saveAuthorOrUpdate(Author author) throws CannotSaveException {
        try {
            authorRepository.saveAndFlush(author);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            throw new CannotSaveException(
                    String.format("Author with id: %d not saved",author.getId()));
        }

    }

    @Override
    public ResponseEntity<Objects> removeAuthor(long id) throws NotFoundException, CannotRemoveException {
        if (authorRepository.findById(id).isEmpty()) throw new NotFoundException(
                String.format("Author with id: %d not found", id));
        try {
            authorRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            throw new CannotRemoveException(e.getMessage());
        }
    }
}
