package com.example.demo.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    private long star;
    private long userId;
    private String feedback;
    public Feedback() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStar(long star) {
        this.star = star;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Feedback(int id, long star, long userId, String feedback) {
        this.id = id;
        this.star = star;
        this.userId = userId;
        this.feedback = feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public long getStar() {
        return star;
    }

    public long getUserId() {
        return userId;
    }

    public String getFeedback() {
        return feedback;
    }
}
