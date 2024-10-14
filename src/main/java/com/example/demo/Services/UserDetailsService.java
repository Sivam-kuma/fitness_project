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
    public List<UserDetails> getUserDetails(Long userId) throws UsernameNotFoundException {
        return userRepository.findByUserId(userId);
    }
    public void deleteUserDetails(Long userId) throws UsernameNotFoundException {
        userRepository.deleteByUserId(userId);
    }
    public Optional<UserDetails> UpdateUserDetails(Long userId,UserDetails userDetails) throws UsernameNotFoundException {
       List<UserDetails> getuserall=userRepository.findByUserId(userId);
        if(getuserall!=null) {
            UserDetails getuser=getuserall.get(Math.toIntExact(userId));
            getuser.setCategories(userDetails.getCategories());
            getuser.setEquipment(userDetails.getEquipment());
            getuser.setGender(userDetails.getGender());
            getuser.setAge(userDetails.getAge());
            getuser.setWeight(userDetails.getWeight());
            return Optional.of(userRepository.save(getuser));
        }
        return Optional.empty();
    }

}
