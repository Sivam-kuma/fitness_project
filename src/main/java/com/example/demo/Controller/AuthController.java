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
import com.example.demo.Services.CustomUserDetailsService;
import com.example.demo.Services.UserDetailsServiceImpl;
import com.example.demo.security.CustomUserDetails;
import com.example.demo.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
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

//    @Autowired
//    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private CustomUserDetailsService userDetailsService;

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

    @PutMapping("/update-password")
    public ResponseEntity<Map<String, String>> updatePassword(@RequestParam String username, @RequestParam String password) {
        Map<String, String> response = new HashMap<>();
        try {
            boolean isUpdated = userDetailsService.updatePassword(username, password);

            if (isUpdated) {
                response.put("message", "Password updated successfully");
                return ResponseEntity.ok(response);
            } else {
                response.put("error", "User not found with username: " + username);
                return ResponseEntity.badRequest().body(response);
            }

        } catch (Exception e) {
            response.put("error", "Password update failed: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping("/authenticate")
    public ResponseEntity<Map<String, Object>> authenticate(@RequestBody JWTRequest jwtRequest) {
        Map<String, Object> response = new HashMap<>();
        String hashedPassword = null; // To store the hashed password for response

        try {
            // Log the username for debugging
            logger.info("Attempting to authenticate user: {}", jwtRequest.getUsername());

            // Load user details for authentication
            UserDetails userDetails = userDetailsService.loadUserByUsername(jwtRequest.getUsername());

            // Check if user exists
            if (userDetails == null) {
                response.put("code", 401);
                response.put("error", "User not found");
                response.put("username", jwtRequest.getUsername());
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }

            // Store the hashed password for response
            hashedPassword = userDetails.getPassword(); // This should be the hashed password from the database

            // Attempt authentication
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword())
            );

            // Generate token if authentication is successful
            Long userId = ((CustomUserDetails) userDetails).getUser().getId(); // Ensure you're calling getId() correctly
            String token = jwtUtil.generateToken(userDetails, userId);

            // Prepare response with token and username
            response.put("username", userDetails.getUsername());
            response.put("token", token);
            response.put("hashedPassword", hashedPassword); // Include hashed password in the response
            return ResponseEntity.ok(response);
        } catch (BadCredentialsException e) {
            logger.warn("Authentication failed for user: {}", jwtRequest.getUsername());

            // Include error details in the response
            response.put("code", 401);
            response.put("error", "Invalid username or password");
            response.put("retrievedUsername", jwtRequest.getUsername()); // Username from request
            response.put("hashedPassword", hashedPassword); // Include hashed password even if authentication fails

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        } catch (Exception e) {
            logger.error("An unexpected error occurred during authentication for user: {}", jwtRequest.getUsername(), e);

            // Handle any other exceptions
            response.put("code", 500);
            response.put("error", "An unexpected error occurred");
            response.put("details", e.getMessage()); // Include exception details for debugging
            response.put("hashedPassword", hashedPassword); // Include hashed password for consistency
            response.put("username", jwtRequest.getUsername()); // Include the attempted username

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }


}


