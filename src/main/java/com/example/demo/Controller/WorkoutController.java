package com.example.demo.Controller;

import com.example.demo.Entity.User;
import com.example.demo.Entity.Workout;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Services.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @PostMapping("/add")
    public ResponseEntity<Workout> addWorkout(@RequestBody Workout workout, Authentication authentication) {
        // Extract username from Authentication
        String username = authentication.getName();
        User user = userRepository.findByUsername(username);
        if (user == null) {
            return ResponseEntity.badRequest().build();
        }

        // Save the workout associated with the user
        Workout savedWorkout = workoutService.saveWorkout(workout.getSessionCalories(), user);
        return ResponseEntity.ok(savedWorkout);
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
