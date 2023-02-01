package com.kasenov.libpro.simplelibrary.dto.dtoImpl;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.kasenov.libpro.simplelibrary.dto.AbstractDto;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@Component
public class Warehouse extends AbstractDto {

    @NotNull(message = "You have not chosen a book")
    @JsonBackReference
    private Book book;
    @Min(value = 0,message = "quantity must must be positive")
    private int quantity;
    @Min(value = 0,message = "quantity at clients must must be positive")
    private int atClients;
}
