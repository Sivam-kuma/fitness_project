package com.example.demo.Entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "days_exercises")
public class DaysExercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    @ElementCollection
    @CollectionTable(name = "day_exercise_mapping", joinColumns = @JoinColumn(name = "days_exercise_id"))
    private List<DayExercise> exercises;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<DayExercise> getExercises() {
        return exercises;
    }

    public void setExercises(List<DayExercise> exercises) {
        this.exercises = exercises;
    }
}

