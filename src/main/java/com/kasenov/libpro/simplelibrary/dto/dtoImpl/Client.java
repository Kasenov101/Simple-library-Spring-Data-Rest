package com.kasenov.libpro.simplelibrary.dto.dtoImpl;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.kasenov.libpro.simplelibrary.dto.AbstractDto;
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
public class Client extends AbstractDto {
    @NotBlank(message = "First name must not be empty")
    @Pattern(regexp = "[A-Z][a-z]+?", message = "invalid first name")
    @JsonDeserialize(converter = StringInitCap.class)
    private String firstName;

    @NotBlank(message = "Last name must not be empty")
    @Pattern(regexp = "[A-Z][a-z]+?", message = "invalid last name")
    @JsonDeserialize(converter = StringInitCap.class)
    private String lastName;

    @Pattern(regexp = "\\d{12}", message = "invalid passport id")
    @NotBlank(message = "passport id must not be empty")
    private String passportId;

    @Pattern(regexp = "[+]\\d[(]\\d{3}[)]-\\d{3}-\\d{2}-\\d{2}", message = "invalid phone number")
    @NotBlank(message = "phone number must not be empty")
    private String phoneNum;
    @NotBlank(message = "address must not be empty")
    private String address;
}
