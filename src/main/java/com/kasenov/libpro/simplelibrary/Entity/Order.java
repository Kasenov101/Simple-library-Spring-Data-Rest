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

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id")
    private List<Book> books;

    @Column(name = "date_of_receiving")
    private LocalDate dateOfReceiving;

    @Column(name = "return_date")
    private LocalDate returnDate;

    @Column(name = "returned_date")
    private LocalDate returnedDate;

    @Column(name = "returned_on_time")
    private boolean returnedOnTime;
}
