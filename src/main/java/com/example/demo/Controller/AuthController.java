//package com.example.demo.Controller;
//
//import com.example.demo.Entity.JWTRequest;
//import com.example.demo.Entity.JWTResponse;
//import com.example.demo.Entity.User;
//import com.example.demo.Services.UserDetailsServiceImpl;
//import com.example.demo.security.CustomUserDetails;
//import com.example.demo.util.JwtUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.web.ErrorResponse;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import static org.springframework.data.jpa.domain.AbstractPersistable_.id;
//
//@RestController
//@CrossOrigin(origins = "https://fitnessproject-production.up.railway.app")
//@RequestMapping("/api/auth")
//public class AuthController {
//
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @Autowired
//    private UserDetailsServiceImpl userDetailsService;
//
//    @Autowired
//    private JwtUtil jwtUtil;
//
//    @PostMapping("/register")
//    public ResponseEntity<?> registerUser(@RequestBody User user) {
//        try {
//            userDetailsService.saveUser(user);
//            return ResponseEntity.ok("User registered successfully");
//        } catch (Exception e) {
//            e.printStackTrace(); // Log the exception for debugging
//            return ResponseEntity.badRequest().body("User registration failed: " + e.getMessage());
//        }
//    }
//
////    @PostMapping("/authenticate")
////    public JWTResponse authenticate(@RequestBody JWTRequest jwtRequest) throws Exception {
////        try {
////            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
////        } catch (AuthenticationException e) {
////            throw new Exception("Invalid username or password");
////        }
////        final org.springframework.security.core.userdetails.UserDetails userDetails = userDetailsService.loadUserByUsername(jwtRequest.getUsername());
////        final String token = jwtUtil.generateToken(userDetails);
////        return new JWTResponse(jwtRequest.getUsername(), token);
////    }
//@PostMapping("/authenticate")
//public ResponseEntity<?> authenticate(@RequestBody JWTRequest jwtRequest) {
//    try {
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword())
//        );
//
//        UserDetails userDetails = userDetailsService.loadUserByUsername(jwtRequest.getUsername());
//        Long userId = ((CustomUserDetails) userDetails).getUser().getId();
//        String token = jwtUtil.generateToken(userDetails ,userId);
//
//        return ResponseEntity.ok(new JWTResponse(jwtRequest.getUsername(), token));
//
//    } catch (AuthenticationException e) {
//        Map<String, Object> errorResponse = new HashMap<>();
//        errorResponse.put("code", 401);
//        errorResponse.put("payload", "Invalid username or password");
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
//    }
//}
//
//
//}

package com.example.demo.Controller;

import com.example.demo.Entity.JWTRequest;
import com.example.demo.Entity.JWTResponse;
import com.example.demo.Entity.User;
import com.example.demo.Services.UserDetailsServiceImpl;
import com.example.demo.security.CustomUserDetails;
import com.example.demo.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@CrossOrigin(origins = "https://fitnessproject-production.up.railway.app")
@RequestMapping("/api/auth")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> registerUser(@RequestBody User user) {
        Map<String, String> response = new HashMap<>();
        try {
            // Save the user using the UserDetailsService
            userDetailsService.saveUser(user);
            response.put("message", "User registered successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("User registration failed", e);
            response.put("error", "User registration failed: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping("/authenticate")
    public ResponseEntity<Map<String, Object>> authenticate(@RequestBody JWTRequest jwtRequest) {
        Map<String, Object> response = new HashMap<>();
        try {
            // Authenticate the user
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword())
            );

            // Load user details for JWT generation
            UserDetails userDetails = userDetailsService.loadUserByUsername(jwtRequest.getUsername());
            Long userId = ((CustomUserDetails) userDetails).getUserId();
            String token = jwtUtil.generateToken(userDetails, userId);

            // Prepare response with token and username
            response.put("username", jwtRequest.getUsername());
            response.put("token", token);
            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            logger.warn("Authentication failed for user: {}", jwtRequest.getUsername());
            response.put("code", 401);
            response.put("error", "Invalid username or password");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }
}


