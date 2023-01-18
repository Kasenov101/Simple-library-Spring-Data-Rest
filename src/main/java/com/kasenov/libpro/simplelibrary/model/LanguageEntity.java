package com.kasenov.libpro.simplelibrary.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "languages")
@Getter
@Setter
@Component
public class LanguageEntity extends AbstractEntity{
    @Column(name = "language")
    private String languageName;
}
