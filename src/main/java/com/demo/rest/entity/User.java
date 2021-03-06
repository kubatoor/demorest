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
    @NotEmpty
    private String userId;
    @Email
    private String email;
    @NotEmpty
    private String name;
}
