package com.example.demo.Entity;

import jakarta.persistence.*;


@Entity
@Table(name = "workouts")
public class Workout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double sessionCalories;
    private double totalCalories = 0; // Initialize to 0

    // Constructors, getters, and setters

    public Workout() {}

    public Workout(double sessionCalories, double totalCalories) {
        this.sessionCalories = sessionCalories;
        this.totalCalories = totalCalories;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getSessionCalories() {
        return sessionCalories;
    }

    public void setSessionCalories(double sessionCalories) {
        this.sessionCalories = sessionCalories;
    }

    public double getTotalCalories() {
        return totalCalories;
    }

    public void setTotalCalories(double totalCalories) {
        this.totalCalories = totalCalories;
    }
}
