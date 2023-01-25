package com.kasenov.libpro.simplelibrary.service.ServiceImpl;

import com.kasenov.libpro.simplelibrary.exceptionHandler.NotFoundException;
import com.kasenov.libpro.simplelibrary.model.EntityImpl.BookEntity;
import com.kasenov.libpro.simplelibrary.repository.BookRepository;
import com.kasenov.libpro.simplelibrary.service.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookService extends AbstractService<BookEntity, BookRepository> {

    private final BookRepository REPOSITORY;

    protected BookService(BookRepository repository, BookRepository repository1) {
        super(repository);
        REPOSITORY = repository1;
    }

    @Transactional(readOnly = true)
    public BookEntity getByIdWhereQuantityAboveZero(long id) throws NotFoundException {
        return REPOSITORY.findByIdAndWarehouseEntity_QuantityGreaterThan(id,0).
                orElseThrow(() -> new NotFoundException(String.format(
                        "book with id: %d is not available at the moment",id)));
    }
}
