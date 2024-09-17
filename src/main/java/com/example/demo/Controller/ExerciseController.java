package com.example.demo.Controller;

import com.example.demo.Entity.ExerciseDetails;
import com.example.demo.Services.ExerciseServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "https://project-production-aec2.up.railway.app")
public class ExerciseController {
    @Autowired
    ExerciseServices exerciseServices;
    @GetMapping("/api/search/exercise")
    public List<ExerciseDetails> searchexerciseagain(@RequestParam String exerciseName){
        List<ExerciseDetails> result=exerciseServices.search( exerciseName);
        return result;

    }

}
