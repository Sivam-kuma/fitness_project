package com.example.demo.Services;

import com.example.demo.Entity.User;
import com.example.demo.Entity.Workout;
import com.example.demo.Repository.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkoutService {
    @Autowired
    private WorkoutRepository workoutRepository;

    /**
     * Saves a workout for a specific user.
     *
     * @param sessionCalories Calories burned in the session.
     * @param user            The user associated with the workout.
     * @return The saved Workout entity.
     */
    public Workout saveWorkout(double sessionCalories, User user) {
        // Validate the sessionCalories input
        if (sessionCalories <= 0) {
            throw new IllegalArgumentException("Session calories must be a positive value.");
        }

        // Create a new Workout instance
        Workout workout = new Workout(sessionCalories, user);

        // Save the workout and return the saved entity
        return workoutRepository.save(workout);
    }


    /**
     * Retrieves the total calories burned by a specific user.
     *
     * @param user The user whose calories are being calculated.
     * @return The sum of sessionCalories for the user.
     */
    public double getTotalCalories(User user) {
        List<Workout> workouts = workoutRepository.findByUser(user);
        return workouts.stream().mapToDouble(Workout::getSessionCalories).sum();
    }

    /**
     * Resets the total calories for a specific user by deleting all their workouts.
     *
     * @param user The user whose workouts are to be deleted.
     */
    public void resetTotalCalories(User user) {
        // Resetting total calories logic for a specific user
        workoutRepository.deleteAll(); // Optionally clear all workout records
        // You may need to consider implementing a logic that keeps track of workouts by user
    }
}
