package com.example.demo.Repository;

import com.example.demo.Entity.Feedback;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {
    Optional<Feedback> findById(int id);
    Optional<Feedback> findByUserId(long userId);
}
