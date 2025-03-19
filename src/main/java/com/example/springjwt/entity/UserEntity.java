package com.example.springjwt.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

/**
 * fileName     : null.java
 * author       : hyunseo
 * date         : 2025. 3. 19.
 * description  :
 */
@Entity
@Setter
@Getter
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;
    private String password;

    private String role;
}
