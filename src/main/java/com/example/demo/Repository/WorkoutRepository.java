package com.example.demo.Repository;

import com.example.demo.Entity.User;
import com.example.demo.Entity.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WorkoutRepository extends JpaRepository<Workout, Long> {
//    List<Workout> findByUser(User user);
    Optional<Workout> findByUserId(Long userId);
}
