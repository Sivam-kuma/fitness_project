package com.example.demo.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "UserDetails")
public class UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id",nullable = false)
    private long Id;
    @Column(name="categories",nullable = false)
    private String Categories;
    @Column(name="gender",nullable = false)
    private String Gender;
    @Column(name="equipment",nullable = false)
    private String Equipment;
    @Column(name="age",nullable = false)
    private float Age;
    @Column(name="weight",nullable = false)
    private float weight;
    @Column(name="userId",nullable = false)
    private long userId;
    public UserDetails() {
    }

    public UserDetails(long id, String categories, String gender, String equipment, float age, float weight,long userId) {
        Id = id;
        Categories = categories;
        Gender = gender;
        Equipment = equipment;
        Age = age;
        this.weight = weight;
       this.userId = userId;

    }

    public long getId() {
        return Id;
    }

    public String getCategories() {
        return Categories;
    }

    public String getGender() {
        return Gender;
    }

    public String getEquipment() {
        return Equipment;
    }

    public float getAge() {
        return Age;
    }

    public float getWeight() {
        return weight;
    }
    public long getUserId() {
        return userId;
    }

    public void setId(long id) {
        Id = id;
    }

    public void setCategories(String categories) {
        Categories = categories;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public void setEquipment(String equipment) {
        Equipment = equipment;
    }

    public void setAge(float age) {
        Age = age;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }
    public void setUserId(long userId) {
        this.userId = userId;
    }
}
