package com.example.demo.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "users") // Added table name to avoid conflicts with reserved keywords
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Ensures auto-increment for the id
    @Column(name = "id", updatable = false, nullable = false) // Ensures id is not updated and is not null
    private Long id;

    @Column(name = "username", nullable = false, unique = true) // Ensures username is unique and not null
    private String username;

    @Column(name = "password", nullable = false) // Ensures password is not null
    private String password;

    // Default constructor
    public User() {
    }

    // Parameterized constructor
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
