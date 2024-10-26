package com.example.demo.Controller;

import com.example.demo.Entity.ExerciseList;
import com.example.demo.Services.ExerciseListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/api/app")
public class ExerciseListController {
    @Autowired  ExerciseListService exerciseListService;
    @GetMapping("/getList/{exerciseName}")
    public ResponseEntity<?> getList(@PathVariable String exerciseName) {
        try {
            List<ExerciseList> exerciseList = exerciseListService.getAllExerciseList(exerciseName);
            return ResponseEntity.ok(exerciseList);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
