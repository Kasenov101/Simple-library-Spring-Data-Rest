package com.kasenov.libpro.simplelibrary.dto.dtoMapper.impl;

import com.kasenov.libpro.simplelibrary.dto.*;
import com.kasenov.libpro.simplelibrary.dto.dtoMapper.ComplexDtoMapper;
import com.kasenov.libpro.simplelibrary.model.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookMapper implements ComplexDtoMapper<BookEntity,Book> {

    @Override
    public Book toDto(BookEntity entity) {
        Book book = SimpleDtoMapper.map(entity, Book.class);
        book.setGenres((List<Genre>) SimpleDtoMapper.mapAll(entity.getGenreEntities(), Genre.class));
        book.setAuthors((List<Author>) SimpleDtoMapper.mapAll(entity.getAuthorEntities(), Author.class));
        book.setLanguage(SimpleDtoMapper.map(entity.getLanguageEntity(), Language.class));
        book.setWarehouse(SimpleDtoMapper.map(entity.getWarehouseEntity(), Warehouse.class));
        return book;
    }

    @Override
    public BookEntity toEntity(Book dto) {
       BookEntity book = SimpleDtoMapper.map(dto,BookEntity.class);
       book.setAuthorEntities((List<AuthorEntity>) SimpleDtoMapper.mapAll(dto.getAuthors(),AuthorEntity.class));
       book.setGenreEntities((List<GenreEntity>) SimpleDtoMapper.mapAll(dto.getGenres(), GenreEntity.class));
       book.setLanguageEntity(SimpleDtoMapper.map(dto.getLanguage(), LanguageEntity.class));
       book.setWarehouseEntity(SimpleDtoMapper.map(dto.getWarehouse(), WarehouseEntity.class));
       return book;
    }
}
