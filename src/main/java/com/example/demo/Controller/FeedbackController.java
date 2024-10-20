package com.example.demo.Controller;

import com.example.demo.Entity.Feedback;
import com.example.demo.Services.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/feedback")
public class FeedbackController {
    @Autowired
    private FeedbackService feedbackService;


    @PostMapping("/save")
    public ResponseEntity<?> feedback(@RequestBody Feedback feedback) {
        try {
            Feedback savedFeedback = feedbackService.saveData(feedback);
            return ResponseEntity.ok(savedFeedback);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }


    }
}
