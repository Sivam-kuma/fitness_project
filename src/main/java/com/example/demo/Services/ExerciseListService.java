package com.example.demo.Services;

import com.example.demo.Entity.ExerciseList;
import com.example.demo.Repository.ExerciseListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciseListService {
    @Autowired  ExerciseListRepository exerciseListRepository;
    public List<ExerciseList> getAllExerciseList(String exerciseName){
         return exerciseListRepository.findByExerciseName(exerciseName);

    }
}
