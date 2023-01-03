package com.kasenov.libpro.simplelibrary.Service;

import com.kasenov.libpro.simplelibrary.Entity.Book;
import com.kasenov.libpro.simplelibrary.Repository.BookRepository;
import org.springframework.stereotype.Service;

@Service
public class BookService extends AbstractService<Book, BookRepository> {
    protected BookService(BookRepository repository) {
        super(repository);
    }
}
