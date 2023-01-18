package com.kasenov.libpro.simplelibrary.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;


@Entity
@Table(name = "authors")
@Getter
@Setter
@Component
public class AuthorEntity extends AbstractEntity{

    @Column(name = "f_name")
    private String firstName;

    @Column(name = "l_name")
    private String lastName;
}
