package com.example.demo.Repository;

import com.example.demo.Entity.DaysExercise;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DaysExerciseRepository extends JpaRepository<DaysExercise, Long> {
    List<DaysExercise> findByUserId(Long userId);
}

