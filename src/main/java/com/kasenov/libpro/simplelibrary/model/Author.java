package com.kasenov.libpro.simplelibrary.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "authors")
@Getter
@Setter
public class Author extends AbstractEntity{

    @Column(name = "f_name")
    private String firstName;

    @Column(name = "l_name")
    private String lastName;
}
