package com.example.demo.Controller;

import com.example.demo.Entity.Exercisedata;
import com.example.demo.Repository.ExerciseDataRepository;
import com.example.demo.Services.ExerciseDataServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "https://fitnessproject-production.up.railway.app")
public class ExerciseDataController {
    @Autowired
    private ExerciseDataServices exerciseDataServices;
    @Autowired
    private ExerciseDataRepository exerciseDataRepository;

    @GetMapping("api/getall")
    public Exercisedata getexercisedata(@RequestBody Exercisedata exerciseData){
        return  exerciseDataServices.saveAllExerciseData(exerciseData);
    }
    @PutMapping("api/update/{id}")
    public ResponseEntity<Exercisedata> updateExerciseData(
            @PathVariable Long id,
            @RequestBody Exercisedata exerciseData) {

        // Call the service to update the data
        Exercisedata updatedData = exerciseDataServices.updateExerciseData(id, exerciseData.getExerciseNamewise());

        // Check if the entity was updated successfully
        if (updatedData != null) {
            return ResponseEntity.ok(updatedData);  // Return 200 OK with the updated data
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);  // Return 404 Not Found
        }
    }

}
