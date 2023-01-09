package com.kasenov.libpro.simplelibrary.model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "books")
@Getter
@Setter
@JsonIdentityInfo(
        scope = Book.class, generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Book extends AbstractEntity{

    @Column(name = "title")
    private String title;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "books_genres",
            joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "genre_id", referencedColumnName = "id"))
    private List<Genre> genres;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "books_authors",
            joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "author_id", referencedColumnName = "id"))
    private List<Author> authors;

    @ManyToOne
    @JoinColumn(name = "language_id")
    private Language language;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @OneToOne(mappedBy = "book", cascade = {CascadeType.ALL})
    private Warehouse warehouse;

}
