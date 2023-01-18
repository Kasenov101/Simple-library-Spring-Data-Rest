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
public class Language extends AbstractDto{
    @JsonDeserialize(converter = StringInitCap.class)
    @Pattern(regexp = "[A-Z][a-z]+?", message = "invalid language name")
    @NotBlank(message = "language must not be empty")
    private String languageName;
}
