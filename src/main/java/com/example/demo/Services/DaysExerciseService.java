package com.example.demo.Services;

import com.example.demo.Entity.DaysExercise;
import com.example.demo.Repository.DaysExerciseRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class DaysExerciseService {

    private final DaysExerciseRepository repository;

    public DaysExerciseService(DaysExerciseRepository repository) {
        this.repository = repository;
    }

    public List<DaysExercise> getAllExercises() {
        return repository.findAll();
    }

    public DaysExercise getExerciseById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Exercise not found"));
    }

    public List<DaysExercise> getExercisesByUserId(Long userId) {
        return repository.findByUserId(userId);
    }

    public DaysExercise saveExercise(DaysExercise exercise) {
        return repository.save(exercise);
    }

    public DaysExercise updateExercise(Long id, DaysExercise newExercise) {
        return repository.findById(id).map(exercise -> {
            exercise.setUserId(newExercise.getUserId());
            exercise.setExercises(newExercise.getExercises());
            return repository.save(exercise);
        }).orElseThrow(() -> new RuntimeException("Exercise not found"));
    }

    public void deleteExercise(Long id) {
        repository.deleteById(id);
    }
}

