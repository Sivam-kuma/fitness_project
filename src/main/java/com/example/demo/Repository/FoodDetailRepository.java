package com.example.demo.Repository;

import com.example.demo.Entity.FoodDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FoodDetailRepository extends JpaRepository<FoodDetail, Long> {
   List<FoodDetail> findById(int id);
   FoodDetail findByUserId(int userId);
}
