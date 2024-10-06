package com.example.demo.security;

import com.example.demo.Entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class CustomUserDetails implements UserDetails {

    private User user;

    public CustomUserDetails(User user) {
        this.user = user;
    }

    // Add the getUser method
    public User getUser() {
        return user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Return the user's authorities/roles
        return null; // Modify this to return actual roles/permissions if needed
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Modify this based on your logic
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Modify this based on your logic
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Modify this based on your logic
    }

    @Override
    public boolean isEnabled() {
        return true; // Modify this based on your logic
    }
}
