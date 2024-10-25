package com.example.demo.Repository;

import com.example.demo.Entity.ExerciseList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExerciseListRepository extends JpaRepository<ExerciseList, Integer> {
    List<ExerciseList> findByEquipmentType(String equipmentType);
}
