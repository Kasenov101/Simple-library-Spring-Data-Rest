package com.kasenov.libpro.simplelibrary.service;

import com.kasenov.libpro.simplelibrary.model.BookEntity;
import com.kasenov.libpro.simplelibrary.repository.BookRepository;
import org.springframework.stereotype.Service;

@Service
public class BookService extends AbstractService<BookEntity, BookRepository> {

    protected BookService(BookRepository repository) {
        super(repository);
    }
}
