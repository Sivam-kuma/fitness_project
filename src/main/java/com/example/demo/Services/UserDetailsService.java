package com.example.demo.Services;

import com.example.demo.Entity.UserDetails;
import com.example.demo.Repository.UserDetailsRepository;
import com.example.demo.Repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class UserDetailsService {
    @Autowired
    private UserDetailsRepository userRepository;
    public UserDetails saveUserDetails(UserDetails userDetails) throws UsernameNotFoundException {
        return userRepository.save(userDetails);
    }
    public Optional<UserDetails> getUserDetails(Long userId) throws UsernameNotFoundException {
        return userRepository.findByUserId(userId);
    }
    public void deleteUserDetails(Long userId) throws UsernameNotFoundException {
        userRepository.deleteByUserId(userId);
    }
    @Transactional
    public Optional<UserDetails> UpdateUserDetails(Long id, UserDetails userDetails) throws UsernameNotFoundException {
        // Fetch the existing user details by id
        Optional<UserDetails> existingUser = userRepository.findById(id);

        // Log the result of the findById query
        if (existingUser.isPresent()) {
            UserDetails getUser = existingUser.get();
            System.out.println("User found: " + getUser);  // Debugging log

            // Update the fields with new data
            getUser.setCategories(userDetails.getCategories());
            getUser.setEquipment(userDetails.getEquipment());
            getUser.setGender(userDetails.getGender());
            getUser.setAge(userDetails.getAge());
            getUser.setWeight(userDetails.getWeight());
            getUser.setHeight(userDetails.getHeight());

            // Save the updated user details and log the result
            UserDetails savedUser = userRepository.saveAndFlush(getUser);

            System.out.println("Updated user saved: " + savedUser);  // Debugging log

            return Optional.of(savedUser);
        } else {
            // If no user found, log that and return Optional.empty()
            System.out.println("No user found with id: " + id);
            return Optional.empty();
        }
    }



}
