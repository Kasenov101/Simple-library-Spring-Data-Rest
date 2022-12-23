package com.kasenov.libpro.simplelibrary.Service;

import com.kasenov.libpro.simplelibrary.Entity.Author;
import com.kasenov.libpro.simplelibrary.Entity.Book;
import com.kasenov.libpro.simplelibrary.Entity.Genre;
import com.kasenov.libpro.simplelibrary.ExceptionHandler.CannotRemoveException;
import com.kasenov.libpro.simplelibrary.ExceptionHandler.CannotSaveException;
import com.kasenov.libpro.simplelibrary.ExceptionHandler.NotFoundException;
import com.kasenov.libpro.simplelibrary.Repository.BookRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class BookServiceImpl implements BookService{

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    @Override
    public List<Book> getAll() throws NotFoundException {
        List<Book> books = bookRepository.findAll();
        if (books.isEmpty()) throw new NotFoundException("Books not found");
        return books;
    }

    @Override
    public Book getById(long id) throws NotFoundException {
        return bookRepository.findById(id).orElseThrow(()->
                new NotFoundException(String.format("Book with id: %s not found", id)));
    }

    @Override
    public ResponseEntity<Objects> saveBook(Book book) throws CannotSaveException {
        try{
            bookRepository.save(book);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            throw new CannotSaveException(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<Objects> updateBook(Book book) throws NotFoundException, CannotSaveException {
        if (bookRepository.findById(book.getId()).isEmpty()) throw
                new NotFoundException(String.format("book with id: %d not found", book.getId()));
        try {
            bookRepository.updateBook(book.getTitle(), (Genre) book.getGenres(),
                    (Author) book.getAuthors(), book.getLanguage(), book.getReleaseDate(),
                    book.getId());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            throw new CannotSaveException(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<Objects> removeBook(long id) throws NotFoundException, CannotRemoveException {
        if (bookRepository.findById(id).isEmpty()) throw
                new NotFoundException(String.format("book with id: %s not found", id));
        try{
            bookRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            throw new CannotRemoveException(e.getMessage());
        }
    }
}
