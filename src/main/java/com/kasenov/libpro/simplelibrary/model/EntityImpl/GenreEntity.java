package com.kasenov.libpro.simplelibrary.model.EntityImpl;

import com.kasenov.libpro.simplelibrary.model.AbstractEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "genres")
@Getter
@Setter
@Component
public class GenreEntity extends AbstractEntity {

    @Column(name = "genre_name")
    private String genreName;

}
