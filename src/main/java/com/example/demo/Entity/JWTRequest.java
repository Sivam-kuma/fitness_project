package com.example.demo.Entity;

public class JWTRequest {
    private String username;
    private String password;

    // No-argument constructor
    public JWTRequest() {
    }

    // Constructor with parameters
    public JWTRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getter and Setter methods
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

