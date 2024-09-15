package com.example.demo.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "FoodSearch")
public class FoodSearch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", updatable = false, nullable = false)
    private int id;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="protein", nullable = false)
    private float protein;

    @Column(name="carbohydrate", nullable = false)
    private float carbohydrate;

    @Column(name="fat", nullable = false)
    private float fat;

    public FoodSearch() {
        // Default constructor
    }

    public FoodSearch(String name, float protein, float carbohydrate, float fat) {
        this.name = name;
        this.protein = protein;
        this.carbohydrate = carbohydrate;
        this.fat = fat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getProtein() {
        return protein;
    }

    public void setProtein(float protein) {
        this.protein = protein;
    }

    public float getCarbohydrate() {
        return carbohydrate;
    }

    public void setCarbohydrate(float carbohydrate) {
        this.carbohydrate = carbohydrate;
    }

    public float getFat() {
        return fat;
    }

    public void setFat(float fat) {
        this.fat = fat;
    }
}
