package com.example.demo.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "workouts")
public class Workout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double sessionCalories;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Constructors

    public Workout() {}

    public Workout(double sessionCalories, User user) {
        this.sessionCalories = sessionCalories;
        this.user = user;
    }

    // Getters and setters

    public Long getId() {
        return id;
    }

    public double getSessionCalories() {
        return sessionCalories;
    }

    public void setSessionCalories(double sessionCalories) {
        this.sessionCalories = sessionCalories;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
