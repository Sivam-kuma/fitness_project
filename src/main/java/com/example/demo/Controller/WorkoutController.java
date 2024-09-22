package com.example.demo.Controller;

import com.example.demo.Entity.Workout;
import com.example.demo.Services.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/workouts")
public class WorkoutController {
    @Autowired
    private WorkoutService workoutService;

    @PostMapping("/add")
    public Workout addWorkout(@RequestBody double sessionCalories) {
        return workoutService.saveWorkout(sessionCalories);
    }

    @GetMapping("/total")
    public double getTotalCalories() {
        return workoutService.getTotalCalories();
    }
}

