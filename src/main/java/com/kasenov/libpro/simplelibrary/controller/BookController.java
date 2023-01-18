package com.kasenov.libpro.simplelibrary.controller;

import com.kasenov.libpro.simplelibrary.dto.Book;
import com.kasenov.libpro.simplelibrary.exceptionHandler.CannotRemoveException;
import com.kasenov.libpro.simplelibrary.exceptionHandler.CannotSaveException;
import com.kasenov.libpro.simplelibrary.exceptionHandler.NotFoundException;
import com.kasenov.libpro.simplelibrary.model.BookEntity;
import com.kasenov.libpro.simplelibrary.model.WarehouseEntity;
import com.kasenov.libpro.simplelibrary.service.BookService;
import com.kasenov.libpro.simplelibrary.service.WarehouseService;
import com.kasenov.libpro.simplelibrary.dto.dtoMapper.impl.BookMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/books")
public class BookController implements CommonController<Book> {
    private final BookService bookService;
    private final WarehouseService warehouseService;
    private final BookMapper bookMapper;

    public BookController(BookService bookService, WarehouseService warehouseService, BookMapper bookMapper) {
        this.bookService = bookService;
        this.warehouseService = warehouseService;
        this.bookMapper = bookMapper;
    }

    @Override
    public List<Book> getAll() throws NotFoundException {
        return bookService.getAll().stream()
                .map(bookMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Book getById(long id) throws NotFoundException {
        return bookMapper.toDto(bookService.getById(id));
    }

    @Override
    public ResponseEntity<Book> save(Book dto) throws CannotSaveException, NotFoundException {
        BookEntity book = bookMapper.toEntity(dto);
        int warehouseQuantity = book.getWarehouseEntity().getQuantity();
        book.setWarehouseEntity(null);
        bookService.save(book);
        WarehouseEntity warehouse = new WarehouseEntity();
        warehouse.setBookEntity(book);
        warehouse.setQuantity(warehouseQuantity);
        warehouseService.save(warehouse);
        book.setWarehouseEntity(warehouse);
        return new ResponseEntity<>(bookMapper.toDto(book), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Book> update(Book dto) throws CannotSaveException, NotFoundException {
        BookEntity book = bookService.update(bookMapper.toEntity(dto));
        return new ResponseEntity<>(bookMapper.toDto(book), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Long> removeById(long id) throws CannotRemoveException, NotFoundException, CannotSaveException {
        return new ResponseEntity<>(bookService.removeById(id),HttpStatus.OK);
    }
}