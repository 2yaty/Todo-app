package com.faculty.demo.user;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
public class User {

    @Id   //PK
    @GeneratedValue         //Sequence
    private Long id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;

    public User( String firstName, String lastName,String email, String password) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User() {
    }
}
