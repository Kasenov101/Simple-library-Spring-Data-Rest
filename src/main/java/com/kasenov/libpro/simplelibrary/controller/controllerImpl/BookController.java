package com.kasenov.libpro.simplelibrary.controller.controllerImpl;

import com.kasenov.libpro.simplelibrary.controller.CommonController;
import com.kasenov.libpro.simplelibrary.dto.dtoImpl.Book;
import com.kasenov.libpro.simplelibrary.exceptionHandler.CannotRemoveException;
import com.kasenov.libpro.simplelibrary.exceptionHandler.CannotSaveException;
import com.kasenov.libpro.simplelibrary.exceptionHandler.NotFoundException;
import com.kasenov.libpro.simplelibrary.model.EntityImpl.BookEntity;
import com.kasenov.libpro.simplelibrary.model.EntityImpl.WarehouseEntity;
import com.kasenov.libpro.simplelibrary.service.ServiceImpl.BookService;
import com.kasenov.libpro.simplelibrary.service.ServiceImpl.WarehouseService;
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
    private final BookService BOOK_SERVICE;
    private final WarehouseService WAREHOUSE_SERVICE;
    private final BookMapper BOOK_MAPPER;

    public BookController(BookService BOOK_SERVICE, WarehouseService WAREHOUSE_SERVICE, BookMapper BOOK_MAPPER) {
        this.BOOK_SERVICE = BOOK_SERVICE;
        this.WAREHOUSE_SERVICE = WAREHOUSE_SERVICE;
        this.BOOK_MAPPER = BOOK_MAPPER;
    }

    @Override
    public List<Book> getAll() throws NotFoundException {
        return BOOK_SERVICE.getAll().stream()
                .map(BOOK_MAPPER::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Book getById(long id) throws NotFoundException {
        return BOOK_MAPPER.toDto(BOOK_SERVICE.getById(id));
    }

    @Override
    public ResponseEntity<Book> save(Book dto) throws CannotSaveException, NotFoundException {
        BookEntity book = BOOK_MAPPER.toEntity(dto);
        int warehouseQuantity = book.getWarehouseEntity().getQuantity();
        book.setWarehouseEntity(null);
        BOOK_SERVICE.save(book);
        WarehouseEntity warehouse = new WarehouseEntity();
        warehouse.setBookEntity(book);
        warehouse.setQuantity(warehouseQuantity);
        WAREHOUSE_SERVICE.save(warehouse);
        book.setWarehouseEntity(warehouse);
        return new ResponseEntity<>(BOOK_MAPPER.toDto(book), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Book> update(Book dto) throws CannotSaveException, NotFoundException {
        BookEntity book = BOOK_SERVICE.update(BOOK_MAPPER.toEntity(dto));
        return new ResponseEntity<>(BOOK_MAPPER.toDto(book), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Long> removeById(long id) throws CannotRemoveException, NotFoundException, CannotSaveException {
        return new ResponseEntity<>(BOOK_SERVICE.removeById(id),HttpStatus.OK);
    }
}