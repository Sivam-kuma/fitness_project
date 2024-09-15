package com.example.demo.Services;

import com.example.demo.Entity.ExerciseDetails;
import com.example.demo.Repository.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciseServices {
    @Autowired
    ExerciseRepository exerciseRepository;
    public List<ExerciseDetails> search(String exerciseName){
         List<ExerciseDetails> result=exerciseRepository.findByExerciseNameContainingIgnoreCase( exerciseName);
         return result;
    }
}
