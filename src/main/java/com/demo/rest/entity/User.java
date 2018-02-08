package com.demo.rest.entity;

import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Min;

@Data
@Entity
public class User {
    @Id
    @Email
    private String email;
    @NotEmpty
    private String name;
    @Min(1)
    private int age;
}
