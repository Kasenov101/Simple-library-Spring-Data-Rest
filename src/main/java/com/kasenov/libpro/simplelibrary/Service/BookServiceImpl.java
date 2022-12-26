package com.kasenov.libpro.simplelibrary.Service;

import com.kasenov.libpro.simplelibrary.Entity.Book;
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
    public ResponseEntity<Objects> saveOrUpdateBook(Book book) throws CannotSaveException {
        try{
            bookRepository.save(book);
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
