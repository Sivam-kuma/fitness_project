package com.example.demo.Controller;

import com.example.demo.Entity.ExerciseList;
import com.example.demo.Services.ExerciseListService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/app")
public class ExerciseListController {

    private static final Logger logger = LoggerFactory.getLogger(ExerciseListController.class);

    @Autowired
    ExerciseListService exerciseListService;

    @GetMapping("/getList/{exerciseName}")
    public ResponseEntity<?> getList(@PathVariable String exerciseName) {
        logger.info("Received request to get exercise list for: {}", exerciseName);
        try {
            List<ExerciseList> exerciseList = exerciseListService.getAllExerciseList(exerciseName);
            if (exerciseList.isEmpty()) {
                logger.warn("No exercises found for: {}", exerciseName);
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(exerciseList);
        } catch (Exception e) {
            logger.error("Error fetching exercise list for {}: {}", exerciseName, e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
}
