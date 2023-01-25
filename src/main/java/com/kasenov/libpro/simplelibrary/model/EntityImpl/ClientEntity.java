package com.kasenov.libpro.simplelibrary.model.EntityImpl;

import com.kasenov.libpro.simplelibrary.model.AbstractEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "clients")
@Getter
@Setter
@Component
public class ClientEntity extends AbstractEntity {

    @Column(name = "f_name")
    private String firstName;

    @Column(name = "l_name")
    private String lastName;

    @Column(name = "passport_id")
    private String passportId;

    @Column(name = "phone_num")
    private String phoneNum;

    @Column(name = "address")
    private String address;
}
