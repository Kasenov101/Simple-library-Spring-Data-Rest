package com.kasenov.libpro.simplelibrary.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "library_warehouse")
@Getter
@Setter
public class Warehouse extends AbstractEntity{

    @OneToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @Column(name = "quantity")
    private int quantity;

}
