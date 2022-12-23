package com.kasenov.libpro.simplelibrary.Repository;

import com.kasenov.libpro.simplelibrary.Entity.Author;
import com.kasenov.libpro.simplelibrary.Entity.Book;
import com.kasenov.libpro.simplelibrary.Entity.Genre;
import com.kasenov.libpro.simplelibrary.Entity.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

public interface BookRepository extends JpaRepository<Book,Long> {
    @Transactional
    @Modifying
    @Query("""
            update Book b set b.title = ?1, b.genres = ?2, b.authors = ?3, b.language = ?4, b.releaseDate = ?5
            where b.id = ?6""")
    int updateBook(String title, Genre genres, Author authors, Language language, LocalDate releaseDate, long id);
}
