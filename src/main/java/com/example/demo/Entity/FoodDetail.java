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
    String carbohydrates;
    String fat;
    String protein;
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

    public String getCarbohydrates() {
        return carbohydrates;
    }

    public String getFat() {
        return fat;
    }

    public String getProtein() {
        return protein;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCarbohydrates(String carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public void setFat(String fat) {
        this.fat = fat;
    }

    public void setProtein(String protein) {
        this.protein = protein;
    }
}
