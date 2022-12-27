package com.kasenov.libpro.simplelibrary.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "languages")
@Getter
@Setter
public class Language extends AbstractEntity{
    @Column(name = "language")
    private String language;
}
