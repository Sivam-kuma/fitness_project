package com.example.demo.Services;

import com.example.demo.Entity.Feedback;
import com.example.demo.Repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedbackService {
    @Autowired
    FeedbackRepository feedbackRepository;
    public Feedback save(Feedback feedback) {
        return feedbackRepository.save(feedback);
    }

}
