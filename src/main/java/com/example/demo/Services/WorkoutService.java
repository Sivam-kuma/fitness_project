package com.example.demo.Services;

import com.example.demo.Entity.Workout;
import com.example.demo.Repository.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkoutService {
    @Autowired
    private WorkoutRepository workoutRepository;

    public Workout saveWorkout(double sessionCalories) {
        double totalCalories = getTotalCalories() + sessionCalories;

        Workout workout = new Workout(sessionCalories, totalCalories);
        return workoutRepository.save(workout);
    }

    public double getTotalCalories() {
        // Sum totalCalories for all workouts
        return workoutRepository.findAll().stream()
                .mapToDouble(Workout::getTotalCalories)
                .sum();
    }

    public void resetTotalCalories() {
        // Resetting total calories logic (if needed)
        workoutRepository.deleteAll(); // Optionally clear all workout records
    }
}

