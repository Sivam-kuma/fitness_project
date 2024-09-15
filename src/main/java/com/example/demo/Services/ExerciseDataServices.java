package com.example.demo.Services;

import com.example.demo.Entity.Exercisedata;
import com.example.demo.Repository.ExerciseDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExerciseDataServices {
    @Autowired
    ExerciseDataRepository exerciseDataRepository;
    public Exercisedata saveAllExerciseData(Exercisedata exerciseData) {
        // Save the exerciseData entity
        return exerciseDataRepository.save(exerciseData);
    }
    public Exercisedata updateExerciseData(Long id, String exerciseNamewise) {
        Exercisedata existingData = exerciseDataRepository.findById(id).orElse(null);
        if (existingData != null) {
            existingData.setExerciseNamewise(exerciseNamewise);
            return exerciseDataRepository.save(existingData); // Return the updated entity after saving
        }
        return null; // Return null if no entity was found
    }

}
