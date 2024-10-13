//package com.example.demo.Controller;
//
//import com.example.demo.Entity.User;
//import com.example.demo.Entity.Workout;
//import com.example.demo.Repository.UserRepository;
//import com.example.demo.Services.WorkoutService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.Authentication;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@RestController
//@CrossOrigin(origins = "https://fitnessproject-production.up.railway.app")
//@RequestMapping("/api/workouts")
//public class WorkoutController {
//    @Autowired
//    private WorkoutService workoutService;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    /**
//     * Endpoint to add a new workout.
//     *
//     * @param workout         The workout details sent in the request body.
//     * @param authentication  The authentication object containing user details.
//     * @return The saved Workout entity or an error response.
//     */
////    @PostMapping("/add")
////    public ResponseEntity<Workout> addWorkout(@RequestBody Workout workout, Authentication authentication) {
////        try {
////            if (authentication == null) {
////                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null); // Token not valid
////            }
////
////            String username = authentication.getName();
////            System.out.println("Retrieved username from authentication: " + username); // Log the username
////
////            User user = userRepository.findByUsername(username);
////            if (user == null) {
////                System.out.println("User not found in the database for username: " + username); // Log user not found
////                return ResponseEntity.badRequest().body(null); // Return error if user is not found
////            }
////
////            System.out.println("User found: " + user.getUsername()); // Log the found user
////
////            // Validate workout object (you can add specific validations as necessary)
////            if (workout == null || workout.getSessionCalories() <= 0) {
////                return ResponseEntity.badRequest().body(null); // Invalid workout data
////            }
////
////            // Save the workout associated with the user
////            Workout savedWorkout = workoutService.saveWorkout(workout.getSessionCalories(), user);
////            return ResponseEntity.ok(savedWorkout);
////        } catch (Exception e) {
////            e.printStackTrace(); // Log the full exception for debugging
////            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // Internal server error
////        }
////    }
//     ///
//    @PostMapping("/add")
//    public ResponseEntity<?> addWorkout(@RequestBody Workout workout, Authentication authentication) {
//        Map<String, Object> response = new HashMap<>();
//        String userIdStr = null; // Initialize userIdStr outside the try block to ensure it's accessible in error cases
//
//        try {
//            // Check if authentication exists
//            if (authentication == null) {
//                response.put("message", "Unauthorized: Missing or invalid token");
//                response.put("userId", userIdStr); // Include userId (even if null)
//                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
//            }
//
//            // Extract userId from the token
//            userIdStr = authentication.getName(); // Assuming `authentication.getName()` returns the userId
//            response.put("userId", userIdStr);
//            System.out.println("Retrieved userId from authentication: " + userIdStr);
//
//            // Try converting the userId to Long
//            Long userId;
//            try {
//                userId = Long.parseLong(userIdStr);
//                response.put("userIdParsed", userId); // Include parsed userId
//            } catch (NumberFormatException e) {
//                response.put("message", "Invalid userId format in token");
//                response.put("error", e.getMessage());
//                return ResponseEntity.badRequest().body(response);
//            }
//
//            // Find user by userId
//            User user = userRepository.findById(userId).orElse(null);
//            if (user == null) {
//                response.put("message", "User not found in the database");
//                return ResponseEntity.badRequest().body(response);
//            }
//
//            response.put("user", user.getUsername());
//
//            // Validate workout data
//            if (workout == null || workout.getSessionCalories() <= 0) {
//                response.put("message", "Invalid workout data");
//                response.put("workoutData", workout); // Include workout data
//                return ResponseEntity.badRequest().body(response);
//            }
//
//            // Save the workout with the associated user
//            Workout savedWorkout = workoutService.saveWorkout(workout.getSessionCalories(), user);
//            response.put("message", "Workout saved successfully");
//            response.put("savedWorkout", savedWorkout); // Include saved workout
//            return ResponseEntity.ok(response);
//        } catch (Exception e) {
//            e.printStackTrace(); // Log the full exception for debugging
//            response.put("error", "Internal server error: " + e.getMessage());
//            response.put("userId", userIdStr); // Include userId even in case of error
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
//        }
//    }
//
//
//
//    /**
//     * Endpoint to retrieve the total calories burned by the authenticated user.
//     *
//     * @param authentication The authentication object containing user details.
//     * @return The total calories burned or an error response.
//     */
//    @GetMapping("/total")
//    public ResponseEntity<Double> getTotalCalories(Authentication authentication) {
//        String username = authentication.getName();
////        User user = userRepository.findByUsername(username);
//        Long id=userRepository.findById(Id);
//        if (id == null) {
//            return ResponseEntity.badRequest().build();
//        }
//
//        double totalCalories = workoutService.getTotalCalories(id);
//        return ResponseEntity.ok(totalCalories);
//    }
//
//    /**
//     * Endpoint to reset the total calories burned for the authenticated user.
//     *
//     * @param authentication The authentication object containing user details.
//     * @return A success message or an error response.
//     */
//    @DeleteMapping("/reset")
//    public ResponseEntity<String> resetTotalCalories(Authentication authentication) {
//        String username = authentication.getName();
//        User user = userRepository.findByUsername(username);
//        if (user == null) {
//            return ResponseEntity.badRequest().body("User not found.");
//        }
//
//        workoutService.resetTotalCalories(user);
//        return ResponseEntity.ok("Total calories reset successfully.");
//    }
//}

//package com.example.demo.Controller;
//
//import com.example.demo.Entity.Workout;
//import com.example.demo.Services.WorkoutService;
//import jakarta.servlet.http.HttpServletRequest;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@CrossOrigin(origins = "https://fitnessproject-production.up.railway.app")
//@RequestMapping("/api/workouts")
//public class WorkoutController {
//
//    @Autowired
//    private WorkoutService workoutService;
//
//    // Save or update workout (accumulate sessionCalories)
//    @PostMapping("/save")
//    public ResponseEntity<?> saveOrUpdateWorkout(
//            @RequestParam double sessionCalories,
//            HttpServletRequest request) {
//        try {
//            Workout workout = workoutService.saveOrUpdateWorkout(sessionCalories, request);
//            return ResponseEntity.ok(workout); // Return 200 OK with workout data
//        } catch (Exception e) {
//            // Debugging information in case of an error
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body("Error saving/updating workout: " + e.getMessage());
//        }
//    }
//
//    // Get the workout data (total calories burned) for the logged-in user
//    @GetMapping
//    public ResponseEntity<?> getWorkout(HttpServletRequest request) {
//        try {
//            Workout workout = workoutService.getWorkout(request);
//            if (workout != null) {
//                return ResponseEntity.ok(workout); // 200 OK with workout data
//            } else {
//                return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                        .body("Workout not found for the logged-in user.");
//            }
//        } catch (Exception e) {
//            // Debugging information in case of an error
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body("Error fetching workout: " + e.getMessage());
//        }
//    }
//
//    // Handle invalid request parameters gracefully
//    @ExceptionHandler(IllegalArgumentException.class)
//    public ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException e) {
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                .body("Invalid input: " + e.getMessage());
//    }
//}
///

//package com.example.demo.Controller;
//
//import com.example.demo.Entity.Workout;
//import com.example.demo.Services.WorkoutService;
//import jakarta.servlet.http.HttpServletRequest;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@CrossOrigin(origins = "https://fitnessproject-production.up.railway.app")
//@RequestMapping("/api/workouts")
//public class WorkoutController {
//
//    @Autowired
//    private WorkoutService workoutService;
//
//    // Save or update workout with sessionCalories from the request body
//    @PostMapping("/save")
//    public ResponseEntity<?> saveOrUpdateWorkout(
//            @RequestBody WorkoutRequest workoutRequest, // Receive JSON in request body
//            HttpServletRequest request) {
//        try {
//            Workout workout = workoutService.saveOrUpdateWorkout(
//                    workoutRequest.getSessionCalories(), request);
//            return ResponseEntity.ok(workout); // 200 OK with workout data
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body("Error saving/updating workout: " + e.getMessage());
//        }
//    }
//
//    // Get the workout data for the logged-in user
//    @GetMapping
//    public ResponseEntity<?> getWorkout(HttpServletRequest request) {
//        try {
//            Workout workout = workoutService.getWorkout(request);
//            return ResponseEntity.ok(workout); // 200 OK with workout data
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body("Error fetching workout: " + e.getMessage());
//        }
//    }
//}
//
//// DTO class to receive sessionCalories in JSON request body
//class WorkoutRequest {
//    private double sessionCalories;
//
//    // Getter and Setter
//    public double getSessionCalories() {
//        return sessionCalories;
//    }
//
//    public void setSessionCalories(double sessionCalories) {
//        this.sessionCalories = sessionCalories;
//    }
//}

///

package com.example.demo.Controller;

import com.example.demo.Entity.Workout;
import com.example.demo.Services.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "https://fitnessproject-production.up.railway.app")
@RequestMapping("/api/workouts")
public class WorkoutController {

    @Autowired
    private WorkoutService workoutService;

    // Save or update workout with sessionCalories and userId from the request body
    @PostMapping("/save")
    public ResponseEntity<?> saveOrUpdateWorkout(@RequestBody Workout workout) {
        try {
            Workout savedWorkout = workoutService.saveOrUpdateWorkout(workout);
            return ResponseEntity.ok(savedWorkout); // 200 OK with workout data
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error saving/updating workout: " + e.getMessage());
        }
    }

    // Get the workout data for the specified userId
    @GetMapping("/{userId}")
    public ResponseEntity<?> getWorkout(@PathVariable Long userId) {
        try {
            Workout workout = workoutService.getWorkout(userId);
            return ResponseEntity.ok(workout); // 200 OK with workout data
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error fetching workout: " + e.getMessage());
        }
    }
}

