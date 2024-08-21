package com.example.demo.Entity;

public class JWTResponse {
    public JWTResponse(String username, String jwtToken) {
        this.username = username;
        this.jwtToken = jwtToken;
    }
    public JWTResponse() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    private String username;
    private String jwtToken;
}
