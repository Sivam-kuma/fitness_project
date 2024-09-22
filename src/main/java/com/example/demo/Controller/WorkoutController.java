package com.example.demo.Controller;

import com.example.demo.Entity.Workout;
import com.example.demo.Services.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "https://project-production-aec2.up.railway.app")
@RequestMapping("/api/workouts")
public class WorkoutController {
    @Autowired
    private WorkoutService workoutService;

    @PostMapping("/add")
    public Workout addWorkout(@RequestBody Workout workout) {
        return workoutService.saveWorkout(workout.getSessionCalories());
    }

    @GetMapping("/total")
    public double getTotalCalories() {
        return workoutService.getTotalCalories();
    }
}
