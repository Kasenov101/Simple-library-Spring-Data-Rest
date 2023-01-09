package com.kasenov.libpro.simplelibrary.service;

import com.kasenov.libpro.simplelibrary.model.Book;
import com.kasenov.libpro.simplelibrary.repository.BookRepository;
import org.springframework.stereotype.Service;

@Service
public class BookService extends AbstractService<Book, BookRepository> {
    protected BookService(BookRepository repository) {
        super(repository);
    }
}
