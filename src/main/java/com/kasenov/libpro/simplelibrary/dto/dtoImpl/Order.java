package com.kasenov.libpro.simplelibrary.dto.dtoImpl;

import com.fasterxml.jackson.annotation.*;
import com.kasenov.libpro.simplelibrary.dto.AbstractDto;
import com.kasenov.libpro.simplelibrary.model.EntityImpl.BookEntity;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
@JsonIgnoreProperties(value = "genres")
public class Order extends AbstractDto {
    @NotNull(message = "client must not be null")
    private Client client;
    @NotEmpty(message = "list books must have at least one book")
    private List<Book> books;
    private LocalDate dateOfReceiving;
    @NotNull(message = "return date must not be null")
    private LocalDate returnDate;
    private LocalDate returnedDate;
    private boolean isReturnedOnTime;
    private boolean orderIsCompleted;
}
