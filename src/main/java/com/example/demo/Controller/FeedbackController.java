package com.example.demo.Controller;

import com.example.demo.Entity.Feedback;
import com.example.demo.Services.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FeedbackController {
    @Autowired
    private FeedbackService feedbackService;

    @RequestMapping("/api/feedback")
    @PostMapping("/save")
    public Feedback feedback(@RequestBody Feedback feedback) {
        return feedbackService.save(feedback);
    }
}
