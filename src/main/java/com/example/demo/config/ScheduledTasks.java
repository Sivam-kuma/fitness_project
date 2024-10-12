package com.example.demo.config;

import com.example.demo.Entity.User;
import com.example.demo.Entity.Workout;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Repository.WorkoutRepository;
import com.example.demo.Services.WorkoutService;
 // Import UserService to get users
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ScheduledTasks {

    @Autowired
    private WorkoutRepository workoutRepository;


    @Autowired
    private UserRepository userService;
    @Scheduled(cron = "0 0 0 * * ?") // Cron expression for midnight every day
    public void resetDailyCalories() {
        Iterable<Workout> workouts = workoutRepository.findAll();
        for (Workout workout : workouts) {
            workout.setSessionCalories(0.0); // Reset session calories to 0
            workoutRepository.save(workout); // Save the updated workout
        }
        System.out.println("All session calories reset to 0 at midnight.");
    }
}
