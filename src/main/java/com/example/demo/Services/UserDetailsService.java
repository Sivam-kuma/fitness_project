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
    public List<UserDetails> getUserDetails() throws UsernameNotFoundException {
        return userRepository.findAll();
    }
    public void deleteUserDetails(Long Id) throws UsernameNotFoundException {
        userRepository.deleteById(Id);
    }
    public Optional<UserDetails> UpdateUserDetails(Long Id,UserDetails userDetails) throws UsernameNotFoundException {
       Optional <UserDetails> getuserall=userRepository.findById( Id);
        if(getuserall!=null) {
            UserDetails getuser=getuserall.get();
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
