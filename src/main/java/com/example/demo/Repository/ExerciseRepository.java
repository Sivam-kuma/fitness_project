package com.example.demo.Repository;

import com.example.demo.Entity.ExerciseDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExerciseRepository extends JpaRepository<ExerciseDetails , Long>{
    List<ExerciseDetails> findByExerciseNameContainingIgnoreCase (String exerciseName);

}
