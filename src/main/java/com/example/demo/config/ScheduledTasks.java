package com.example.demo.config;

import com.example.demo.Services.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    @Autowired
    private WorkoutService workoutService;

    @Scheduled(cron = "0 0 0 * * ?") // This will run at midnight every day
    public void resetCalories() {
        workoutService.resetTotalCalories();
        System.out.println("Total calories reset to 0 at midnight.");
    }
}

