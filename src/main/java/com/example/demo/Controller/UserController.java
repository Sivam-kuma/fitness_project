package com.example.demo.Controller;

import com.example.demo.Entity.UserDetails;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Services.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin(origins = "https://project-production-aec2.up.railway.app")
@RequestMapping("/api/userdetails")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserDetailsService userDetailsService;
    @PutMapping("/{Id}")
    public ResponseEntity<UserDetails> updateUser(@PathVariable Long Id, @RequestBody UserDetails userDetails) {
        return userDetailsService.UpdateUserDetails(Id ,userDetails ).map(updateUser->ResponseEntity.ok().body(updateUser)).orElse(ResponseEntity.notFound().build());
    }
    @PostMapping("/create")
    public ResponseEntity<UserDetails> createUser(@RequestBody UserDetails userDetails) {
        UserDetails CreateUserDetails = userDetailsService.saveUserDetails(userDetails);
        return ResponseEntity.ok().body(CreateUserDetails);
    }
    @GetMapping("/getAll")
    public ResponseEntity<List<UserDetails>> getAllUsers() {
        List<UserDetails> userDetailsList=userDetailsService.getUserDetails();
        return ResponseEntity.ok().body(userDetailsList);
    }
    @DeleteMapping("/delete/{Id}")
    public ResponseEntity<UserDetails> deleteUser(@PathVariable Long Id) {
        userDetailsService.deleteUserDetails(Id);
        return ResponseEntity.ok().build();
    }
}
