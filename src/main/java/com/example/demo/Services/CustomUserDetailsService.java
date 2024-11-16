package com.example.demo.Services;

import com.example.demo.Entity.User;
import com.example.demo.Repository.UserRepository;
import com.example.demo.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service // Marks this class as a Spring service
public class CustomUserDetailsService implements UserDetailsService { // Implement the interface

    @Autowired
    private UserRepository userRepository; // Inject the UserRepository to retrieve user data
    @Autowired
    @Lazy
    private PasswordEncoder passwordEncoder;

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        // Fetch user from the database by username
//        User user = userRepository.findByUsername(username);
//
//        // If the user is not found, throw an exception
//        if (user == null) {
//            throw new UsernameNotFoundException("User not found with username: " + username);
//        }
//
//        // Return an instance of CustomUserDetails containing the user
//        return new CustomUserDetails(user);
//    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        return new CustomUserDetails(user); // Ensure this returns your custom class
    }


    public User saveUser(User user) {
        // Encrypt the password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
//    public UserDetails loadUserById(Long userId) throws UsernameNotFoundException {
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found with ID: " + userId));
//        return new org.springframework.security.core.userdetails.User(
//                user.getUsername(),
//                user.getPassword(),
//                Collections.singleton(new SimpleGrantedAuthority("USER")));
//    }

    public CustomUserDetails loadUserById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with id: " + userId));
        return new CustomUserDetails(user); // Ensure this returns your custom class
    }


    public boolean updatePassword(String username, String newPassword) {
        // Find the user by username
        User user = userRepository.findByUsername(username);

        if (user != null) {
            user.setPassword(newPassword); // Update the password
            userRepository.save(user); // Save the updated user
            return true; // Return true if password updated successfully
        }
        return false; // Return false if user not found
    }



}
