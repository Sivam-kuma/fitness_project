package com.example.demo.Services;

import com.example.demo.Entity.UserDetails;
import com.example.demo.Repository.UserDetailsRepository;
import com.example.demo.Repository.UserRepository;
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
    public Optional<UserDetails> UpdateUserDetails(Long userId, UserDetails userDetails) throws UsernameNotFoundException {
        Optional<UserDetails> existingUser = userRepository.findByUserId(userId);

        if (existingUser.isPresent()) {
            UserDetails getUser = existingUser.get();
            getUser.setCategories(userDetails.getCategories());
            getUser.setEquipment(userDetails.getEquipment());
            getUser.setGender(userDetails.getGender());
            getUser.setAge(userDetails.getAge());
            getUser.setWeight(userDetails.getWeight());

            return Optional.of(userRepository.save(getUser));
        }
        return Optional.empty();
    }


}
