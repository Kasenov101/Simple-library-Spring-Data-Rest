package com.kasenov.libpro.simplelibrary.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.kasenov.libpro.simplelibrary.dto.deserializationConvertor.StringInitCap;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@Component
public class Author extends AbstractDto{

    @JsonDeserialize(converter = StringInitCap.class)
    @NotBlank(message = "First name must not be empty")
    @Pattern(regexp = "[A-Z][a-z]+?", message = "invalid first name")
    private String firstName;

    @JsonDeserialize(converter = StringInitCap.class)
    @NotBlank(message = "Last name must not be empty")
    @Pattern(regexp = "[A-Z][a-z]+?", message = "invalid last name")
    private String lastName;

}
