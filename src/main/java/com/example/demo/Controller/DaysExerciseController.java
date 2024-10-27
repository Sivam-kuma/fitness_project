package com.example.demo.Controller;

import com.example.demo.Entity.DaysExercise;
import com.example.demo.Services.DaysExerciseService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/exercises")
public class DaysExerciseController {

    private final DaysExerciseService service;

    public DaysExerciseController(DaysExerciseService service) {
        this.service = service;
    }

    @GetMapping
    public List<DaysExercise> getAllExercises() {
        return service.getAllExercises();
    }

    @GetMapping("/{id}")
    public DaysExercise getExerciseById(@PathVariable Long id) {
        return service.getExerciseById(id);
    }

    @GetMapping("/user/{userId}")
    public List<DaysExercise> getExercisesByUserId(@PathVariable Long userId) {
        return service.getExercisesByUserId(userId);
    }

    @PostMapping
    public DaysExercise createExercise(@RequestBody DaysExercise exercise) {
        return service.saveExercise(exercise);
    }

    @PutMapping("/{id}")
    public DaysExercise updateExercise(@PathVariable Long id, @RequestBody DaysExercise newExercise) {
        return service.updateExercise(id, newExercise);
    }

    @DeleteMapping("/{id}")
    public void deleteExercise(@PathVariable Long id) {
        service.deleteExercise(id);
    }
}

