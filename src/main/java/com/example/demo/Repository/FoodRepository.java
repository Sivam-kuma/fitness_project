package com.example.demo.Repository;

import com.example.demo.Entity.FoodSearch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface FoodRepository extends JpaRepository<FoodSearch,Integer> {
    List<FoodSearch> findByNameContainingIgnoreCase(String name);
}
