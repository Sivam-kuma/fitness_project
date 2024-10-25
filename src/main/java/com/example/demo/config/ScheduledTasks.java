package com.example.demo.config;

import com.example.demo.Entity.FoodDetail;
import com.example.demo.Entity.User;
import com.example.demo.Entity.Workout;
import com.example.demo.Repository.FoodDetailRepository;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Repository.WorkoutRepository;
import com.example.demo.Services.WorkoutService;
 // Import UserService to get users
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class ScheduledTasks {

    @Autowired
    private WorkoutRepository workoutRepository;


    @Autowired
    private UserRepository userService;
    @Scheduled(cron = "0 2 0 * * ?", zone = "Asia/Kolkata")  // Cron expression for midnight every day
    public void resetDailyCalories() {
        Iterable<Workout> workouts = workoutRepository.findAll();
        for (Workout workout : workouts) {
            workout.setSessionCalories(0.0); // Reset session calories to 0
            workoutRepository.save(workout); // Save the updated workout
        }
        System.out.println("All session calories reset to 0 at midnight.");
    }

    @Autowired
    FoodDetailRepository foodDetailRepository;
    @Scheduled(cron = "0 2 0 * * ?", zone = "Asia/Kolkata")
    public void resetFoodCalories() {
        Iterable<FoodDetail> foodDetails = foodDetailRepository.findAll();
        for (FoodDetail foodDetail : foodDetails) {
            foodDetail.setCarbohydrates(0.0);
            foodDetail.setFat(0.0);
            foodDetail.setProtein(0.0);
            foodDetailRepository.save(foodDetail);
        }
        System.out.println("All food calories reset to 0 at midnight.");
    }


    @Scheduled(fixedDelay = 60000)  // Runs every 1 minute (60,000ms)
    public void keepServerActive() {
        System.out.println("Server is active at: " + java.time.LocalDateTime.now());
    }

}
