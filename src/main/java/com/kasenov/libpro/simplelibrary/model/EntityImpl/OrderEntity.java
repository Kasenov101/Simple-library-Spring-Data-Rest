package com.kasenov.libpro.simplelibrary.model.EntityImpl;

import com.kasenov.libpro.simplelibrary.model.AbstractEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
@Component
public class OrderEntity extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "client_id")
    private ClientEntity clientEntity;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "books_orders",
            joinColumns = @JoinColumn(name = "order_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"))
    private List<BookEntity> bookEntities;

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
