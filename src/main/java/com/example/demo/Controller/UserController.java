package com.example.demo.Controller;

import com.example.demo.Entity.UserDetails;
import com.example.demo.Services.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@CrossOrigin(origins = "https://fitnessproject-production.up.railway.app")
@RequestMapping("/api/userdetails")
public class UserController {
//    @Autowired
//    private UserDetailsRepository userRepository;
    @Autowired
    private UserDetailsService userDetailsService;
    @PutMapping("/{id}")
    public ResponseEntity<UserDetails> updateUser(@PathVariable Long id, @RequestBody UserDetails userDetails) {
        return userDetailsService.UpdateUserDetails(id, userDetails)
                .map(updateUser -> ResponseEntity.ok().body(updateUser))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public ResponseEntity<UserDetails> createUser(@RequestBody UserDetails userDetails) {
        UserDetails CreateUserDetails = userDetailsService.saveUserDetails(userDetails);
        return ResponseEntity.ok().body(CreateUserDetails);
    }
    @GetMapping("/getAll/{userId}")
    public ResponseEntity<Optional<UserDetails>> getAllUsers(@PathVariable Long userId) {
        Optional<UserDetails> userDetailsList=userDetailsService.getUserDetails(userId);
        return ResponseEntity.ok().body(userDetailsList);
    }
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<UserDetails> deleteUser(@PathVariable Long userId) {
        userDetailsService.deleteUserDetails(userId);
        return ResponseEntity.ok().build();
    }
}


