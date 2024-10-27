package com.example.demo.Entity;

import jakarta.persistence.Embeddable;

@Embeddable
public class DayExercise {

    private String day;
    private String exerciseName;

    // Getters and Setters
    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }
}

