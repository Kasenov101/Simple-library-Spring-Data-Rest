package com.kasenov.libpro.simplelibrary.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "genres")
@Getter
@Setter
public class Genre extends AbstractEntity{

    @Column(name = "genre_name")
    private String genreName;

    @ManyToMany
    @JoinTable(name = "books_genres",joinColumns = @JoinColumn(name = "genre_id"),
    inverseJoinColumns = @JoinColumn(name = "book_id"))
    private List<Book> books;


    public void addBook(Book book) {
        if (books == null) {
            books = new ArrayList<>();
        }
        books.add(book);
    }
}
