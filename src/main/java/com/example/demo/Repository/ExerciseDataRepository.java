package com.example.demo.Repository;

import com.example.demo.Entity.Exercisedata;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

@Repository
public interface ExerciseDataRepository extends JpaRepository<Exercisedata , Long>{
//    List<Exercisedata> save(String exerciseNamewise);

}
