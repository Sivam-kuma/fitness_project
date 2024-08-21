package com.example.demo.Controller;

import com.example.demo.Entity.JWTRequest;
import com.example.demo.Entity.JWTResponse;
import com.example.demo.Entity.User;
import com.example.demo.Services.UserDetailsServiceImpl;
import com.example.demo.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        try {
            userDetailsService.saveUser(user);
            return ResponseEntity.ok("User registered successfully");
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception for debugging
            return ResponseEntity.badRequest().body("User registration failed: " + e.getMessage());
        }
    }

//    @PostMapping("/authenticate")
//    public JWTResponse authenticate(@RequestBody JWTRequest jwtRequest) throws Exception {
//        try {
//            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
//        } catch (AuthenticationException e) {
//            throw new Exception("Invalid username or password");
//        }
//        final org.springframework.security.core.userdetails.UserDetails userDetails = userDetailsService.loadUserByUsername(jwtRequest.getUsername());
//        final String token = jwtUtil.generateToken(userDetails);
//        return new JWTResponse(jwtRequest.getUsername(), token);
//    }
@PostMapping("/authenticate")
public ResponseEntity<?> authenticate(@RequestBody JWTRequest jwtRequest) {
    try {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword())
        );

        UserDetails userDetails = userDetailsService.loadUserByUsername(jwtRequest.getUsername());
        String token = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JWTResponse(jwtRequest.getUsername(), token));

    } catch (AuthenticationException e) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("code", 401);
        errorResponse.put("payload", "Invalid username or password");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
    }
}


}
