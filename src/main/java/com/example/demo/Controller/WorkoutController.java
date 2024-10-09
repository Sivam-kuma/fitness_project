package com.example.demo.Controller;

import com.example.demo.Entity.User;
import com.example.demo.Entity.Workout;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Services.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "https://fitnessproject-production.up.railway.app")
@RequestMapping("/api/workouts")
public class WorkoutController {
    @Autowired
    private WorkoutService workoutService;

    @Autowired
    private UserRepository userRepository;

    /**
     * Endpoint to add a new workout.
     *
     * @param workout         The workout details sent in the request body.
     * @param authentication  The authentication object containing user details.
     * @return The saved Workout entity or an error response.
     */
//    @PostMapping("/add")
//    public ResponseEntity<Workout> addWorkout(@RequestBody Workout workout, Authentication authentication) {
//        try {
//            if (authentication == null) {
//                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null); // Token not valid
//            }
//
//            String username = authentication.getName();
//            System.out.println("Retrieved username from authentication: " + username); // Log the username
//
//            User user = userRepository.findByUsername(username);
//            if (user == null) {
//                System.out.println("User not found in the database for username: " + username); // Log user not found
//                return ResponseEntity.badRequest().body(null); // Return error if user is not found
//            }
//
//            System.out.println("User found: " + user.getUsername()); // Log the found user
//
//            // Validate workout object (you can add specific validations as necessary)
//            if (workout == null || workout.getSessionCalories() <= 0) {
//                return ResponseEntity.badRequest().body(null); // Invalid workout data
//            }
//
//            // Save the workout associated with the user
//            Workout savedWorkout = workoutService.saveWorkout(workout.getSessionCalories(), user);
//            return ResponseEntity.ok(savedWorkout);
//        } catch (Exception e) {
//            e.printStackTrace(); // Log the full exception for debugging
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // Internal server error
//        }
//    }
     ///
    @PostMapping("/add")
    public ResponseEntity<?> addWorkout(@RequestBody Workout workout, Authentication authentication) {
        Map<String, Object> response = new HashMap<>();
        try {
            if (authentication == null) {
                response.put("message", "Unauthorized: Missing or invalid token");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }

            // Extract userId from the token
            String userIdStr = authentication.getName(); // Assuming `authentication.getName()` returns the userId
            response.put("userId", userIdStr);
            System.out.println("Retrieved userId from authentication: " + userIdStr); // Log the userId

            // Convert the userId to Long type
            Long userId;
            try {
                userId = Long.parseLong(userIdStr);
            } catch (NumberFormatException e) {
                response.put("message", "Invalid userId format in token");
                return ResponseEntity.badRequest().body(response);
            }

            // Find user by userId
            User user = userRepository.findById(userId).orElse(null);
            if (user == null) {
                response.put("message", "User not found in the database");
                return ResponseEntity.badRequest().body(response);
            }

            response.put("user", user.getUsername());
            System.out.println("User found: " + user.getUsername()); // Log the found user

            // Validate the workout data
            if (workout == null || workout.getSessionCalories() <= 0) {
                response.put("message", "Invalid workout data");
                return ResponseEntity.badRequest().body(response);
            }

            // Save the workout
            Workout savedWorkout = workoutService.saveWorkout(workout.getSessionCalories(), user);
            response.put("message", "Workout saved successfully");
            response.put("workout", savedWorkout);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace(); // Log the full exception for debugging
            response.put("error", "Internal server error: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }


    /**
     * Endpoint to retrieve the total calories burned by the authenticated user.
     *
     * @param authentication The authentication object containing user details.
     * @return The total calories burned or an error response.
     */
    @GetMapping("/total")
    public ResponseEntity<Double> getTotalCalories(Authentication authentication) {
        String username = authentication.getName();
        User user = userRepository.findByUsername(username);
        if (user == null) {
            return ResponseEntity.badRequest().build();
        }

        double totalCalories = workoutService.getTotalCalories(user);
        return ResponseEntity.ok(totalCalories);
    }

    /**
     * Endpoint to reset the total calories burned for the authenticated user.
     *
     * @param authentication The authentication object containing user details.
     * @return A success message or an error response.
     */
    @DeleteMapping("/reset")
    public ResponseEntity<String> resetTotalCalories(Authentication authentication) {
        String username = authentication.getName();
        User user = userRepository.findByUsername(username);
        if (user == null) {
            return ResponseEntity.badRequest().body("User not found.");
        }

        workoutService.resetTotalCalories(user);
        return ResponseEntity.ok("Total calories reset successfully.");
    }
}
