//package com.example.demo.Services;
//
//import com.example.demo.Entity.User;
//import com.example.demo.Entity.Workout;
//import com.example.demo.Repository.WorkoutRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class WorkoutService {
//    @Autowired
//    private WorkoutRepository workoutRepository;
//
//    /**
//     * Saves a workout for a specific user.
//     *
//     * @param sessionCalories Calories burned in the session.
//     * @param user            The user associated with the workout.
//     * @return The saved Workout entity.
//     */
//    public Workout saveWorkout(double sessionCalories, User user) {
//        // Validate the sessionCalories input
//        if (sessionCalories <= 0) {
//            throw new IllegalArgumentException("Session calories must be a positive value.");
//        }
//
//        // Create a new Workout instance
//        Workout workout = new Workout(sessionCalories, user);
//
//        // Save the workout and return the saved entity
//        return workoutRepository.save(workout);
//    }
//
//
//    /**
//     * Retrieves the total calories burned by a specific user.
//     *
//     * @param user The user whose calories are being calculated.
//     * @return The sum of sessionCalories for the user.
//     */
//    public double getTotalCalories(User user,Long id) {
//        List<Workout> workouts = workoutRepository.findByUserId(id);
//        return workouts.stream().mapToDouble(Workout::getSessionCalories).sum();
//    }
//
//    /**
//     * Resets the total calories for a specific user by deleting all their workouts.
//     *
//     * @param user The user whose workouts are to be deleted.
//     */
//    public void resetTotalCalories(User user) {
//        // Resetting total calories logic for a specific user
//        workoutRepository.deleteAll(); // Optionally clear all workout records
//        // You may need to consider implementing a logic that keeps track of workouts by user
//    }
//}

//package com.example.demo.Services;
//
//import com.example.demo.Entity.Workout;
//import com.example.demo.Repository.WorkoutRepository;
//import com.example.demo.util.JwtUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import jakarta.servlet.http.HttpServletRequest;
//import java.util.Optional;
//
//@Service
//public class WorkoutService {
//
//    @Autowired
//    private WorkoutRepository workoutRepository;
//
//    @Autowired
//    private JwtUtil jwtUtil; // Use JwtUtil to extract userId
//
//    // Save or update workout data by adding new sessionCalories
//    public Workout saveOrUpdateWorkout(double sessionCalories, HttpServletRequest request) {
//        Long userId = getUserIdFromToken(request); // Get userId from JWT token
//
//        Optional<Workout> existingWorkout = workoutRepository.findByUserId(userId);
//
//        Workout workout;
//        if (existingWorkout.isPresent()) {
//            workout = existingWorkout.get();
//            workout.setSessionCalories(workout.getSessionCalories() + sessionCalories);
//        } else {
//            workout = new Workout(sessionCalories, userId);
//        }
//
//        return workoutRepository.save(workout);
//    }
//
//    // Fetch all workouts for the logged-in user
//    public Workout getWorkout(HttpServletRequest request) {
//        Long userId = getUserIdFromToken(request);
//        return workoutRepository.findByUserId(userId).orElse(new Workout(0.0, userId));
//    }
//
//    // Use JwtUtil to extract userId from JWT token in the Authorization header
//    private Long getUserIdFromToken(HttpServletRequest request) {
//        String authorizationHeader = request.getHeader("Authorization");
//        String token = authorizationHeader.substring(7); // Remove "Bearer " prefix
//        return jwtUtil.extractUserId(token); // Extract userId using JwtUtil
//    }
//}

///
package com.example.demo.Services;

import com.example.demo.Entity.Workout;
import com.example.demo.Repository.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WorkoutService {

    @Autowired
    private WorkoutRepository workoutRepository;

    // Save or update workout data by adding new sessionCalories
    public Workout saveOrUpdateWorkout(Workout workout) {
        Long userId = workout.getUserId();
        Optional<Workout> existingWorkout = workoutRepository.findByUserId(userId);

        if (existingWorkout.isPresent()) {
            Workout updatedWorkout = existingWorkout.get();
            updatedWorkout.setSessionCalories(
                    updatedWorkout.getSessionCalories() + workout.getSessionCalories()
            );
            return workoutRepository.save(updatedWorkout);
        } else {
            return workoutRepository.save(workout);
        }
    }

    // Fetch all workouts for the specified userId
    public Workout getWorkout(Long userId) {
        return workoutRepository.findByUserId(userId).orElse(new Workout(0.0, userId));
    }
}

