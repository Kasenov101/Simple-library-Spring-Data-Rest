package com.kasenov.libpro.simplelibrary.dto.dtoImpl;

import com.fasterxml.jackson.annotation.*;
import com.kasenov.libpro.simplelibrary.dto.AbstractDto;
import com.kasenov.libpro.simplelibrary.model.EntityImpl.BookEntity;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@Component
@JsonIdentityInfo(
        scope = BookEntity.class, generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Book extends AbstractDto {

    @Pattern(regexp = "^([a-zA-Z0-9]+([,.!?$%-]?)(\\s)?)+$", message = "invalid title")
    @NotEmpty(message = "Title must not be empty")
    private String title;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotEmpty(message = "genres must not be empty")
    private List<Genre> genres;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotEmpty(message = "authors must not be empty")
    private List<Author> authors;

    @NotNull(message = "language must not be null")
    private Language language;
    @NotNull(message = "release date must not be null")
    private LocalDate releaseDate;
    @NotNull(message = "warehouse info must not be null")
    @JsonManagedReference
    private Warehouse warehouse;
}
