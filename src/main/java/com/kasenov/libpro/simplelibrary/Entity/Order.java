package com.kasenov.libpro.simplelibrary.Entity;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
@JsonIdentityInfo(
        scope = Book.class, generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Order extends AbstractEntity{

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToMany
    @JoinTable(name = "books_orders",
            joinColumns = @JoinColumn(name = "order_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"))
    private List<Book> books;

    @Column(name = "date_of_receiving")
    private LocalDate dateOfReceiving;

    @Column(name = "return_date")
    private LocalDate returnDate;

    @Column(name = "returned_date")
    private LocalDate returnedDate;

    @Column(name = "is_returned_on_time")
    private boolean isReturnedOnTime;

    @Column(name = "order_is_completed")
    private boolean orderIsCompleted;
}
