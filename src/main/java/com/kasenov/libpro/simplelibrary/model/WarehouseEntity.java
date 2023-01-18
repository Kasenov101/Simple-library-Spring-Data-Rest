package com.kasenov.libpro.simplelibrary.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "library_warehouse")
@Getter
@Setter
@Component
public class WarehouseEntity extends AbstractEntity{

    @OneToOne
    @JoinColumn(name = "book_id")
    private BookEntity bookEntity;

    @Column(name = "quantity")
    private int quantity;

}
