package com.example.demo.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class Exercisedata {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long id;
    public String exerciseNamewise;

    public Exercisedata() {
    }

    public Exercisedata(long id, String exerciseNamewise) {
        this.id = id;
        this.exerciseNamewise = exerciseNamewise;
    }

    public long getId() {
        return id;
    }

    public String getExerciseNamewise() {
        return exerciseNamewise;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setExerciseNamewise(String exerciseNamewise) {
        this.exerciseNamewise = exerciseNamewise;
    }
}
