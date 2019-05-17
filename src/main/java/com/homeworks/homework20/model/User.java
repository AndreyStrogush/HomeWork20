package com.homeworks.homework20.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "users")
@Accessors(chain = true)
public class User extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    @Email
    @Column(name = "email")
    private String email;

    @Column(name = "birthday")
    private LocalDate birthday;

}
