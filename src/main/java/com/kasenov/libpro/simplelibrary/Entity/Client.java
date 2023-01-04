package com.kasenov.libpro.simplelibrary.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "clients")
@Getter
@Setter
public class Client extends AbstractEntity{

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
