package com.kasenov.libpro.simplelibrary.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "books")
@Getter
@Setter
public class Book extends AbstractEntity{

    @Column(name = "title")
    private String title;

    @ManyToMany
    @JoinTable(name = "books_genres", joinColumns = @JoinColumn(name = "book_id"),
    inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private List<Genre> genres;

    @ManyToMany
    @JoinTable(name = "books_authors", joinColumns = @JoinColumn(name = "book_id"),
    inverseJoinColumns = @JoinColumn(name = "author_id"))
    private List<Author> authors;

    @ManyToOne
    @JoinColumn(name = "language_id")
    private Language language;

    @Column(name = "release_date")
    private LocalDate releaseDate;


    public void addGenre(Genre genre) {
        if (genre == null) {
            genres = new ArrayList<>();
        }
        genres.add(genre);
    }

    public void addAuthor(Author author) {
        if (authors == null) {
            authors = new ArrayList<>();
        }
        authors.add(author);
    }

}
