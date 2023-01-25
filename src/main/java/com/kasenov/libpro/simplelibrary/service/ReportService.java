package com.kasenov.libpro.simplelibrary.service;

import com.kasenov.libpro.simplelibrary.dto.projection.BooksAreOver;
import com.kasenov.libpro.simplelibrary.exceptionHandler.NotFoundException;
import com.kasenov.libpro.simplelibrary.model.EntityImpl.OrderEntity;
import com.kasenov.libpro.simplelibrary.repository.BookRepository;
import com.kasenov.libpro.simplelibrary.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class ReportService {
    private final BookRepository BOOK_REPOSITORY;

    private final OrderRepository ORDER_REPOSITORY;

    public ReportService(BookRepository BOOK_REPOSITORY, OrderRepository ORDER_REPOSITORY) {
        this.BOOK_REPOSITORY = BOOK_REPOSITORY;
        this.ORDER_REPOSITORY = ORDER_REPOSITORY;
    }

    public List<BooksAreOver> findWhereQuantityAndAtClientIsZero() throws NotFoundException {
        List<BooksAreOver> list = BOOK_REPOSITORY.findWhereQuantityAndAtClientIsZero();
        if (list.isEmpty()) throw new NotFoundException("books with quantity is zero not found");
        return list;
    }

    public List<OrderEntity> findAllReturnTimeOut() throws NotFoundException {
        LocalDate date = LocalDate.now();
        List<OrderEntity> list = ORDER_REPOSITORY.findByOrderIsCompletedFalseAndReturnDateBefore(date);
        if (list.isEmpty()) throw new NotFoundException("Orders with return time out not found");
        return list;
    }
}
