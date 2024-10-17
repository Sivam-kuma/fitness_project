package com.example.demo.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class FoodDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    Double carbohydrates;
    Double fat;
    Double protein;
    int userId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public FoodDetail() {
    }

    public int getId() {
        return id;
    }

    public Double getCarbohydrates() {
        return carbohydrates;
    }

    public Double getFat() {
        return fat;
    }

    public Double getProtein() {
        return protein;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCarbohydrates(Double carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public void setFat(Double fat) {
        this.fat = fat;
    }

    public void setProtein(Double protein) {
        this.protein = protein;
    }
}
