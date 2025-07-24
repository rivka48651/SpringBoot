package com.mysite.myspringboot.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import lombok.Data;

@Entity
@Table
@Data
public class User {
    @Id
    @GeneratedValue
    private int id;

    private String email;
    private String password;
}
