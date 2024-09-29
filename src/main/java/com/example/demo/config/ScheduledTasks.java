package com.example.demo.config;

import com.example.demo.Entity.User;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Services.WorkoutService;
 // Import UserService to get users
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ScheduledTasks {

    @Autowired
    private WorkoutService workoutService;

    @Autowired
    private UserRepository userService;
    @Scheduled(cron = "0 0 0 * * ?") // This will run at midnight every day
    public void resetCalories() {
        List<User> users = userService.findAll(); // Fetch all users
        for (User user : users) {
            workoutService.resetTotalCalories(user); // Reset calories for each user
        }
        System.out.println("Total calories reset to 0 at midnight for all users.");
    }
}
