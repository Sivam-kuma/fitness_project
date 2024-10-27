package com.example.demo.Services;




import com.example.demo.Entity.FoodItem;
import com.example.demo.Repository.FoodItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodItemService {
    @Autowired
    private FoodItemRepository foodItemRepository;

    public List<FoodItem> getAllFoodItems() {
        return foodItemRepository.findAll();
    }

    public FoodItem getFoodItemById(Long id) {
        return foodItemRepository.findById(id).orElse(null);
    }

    public List<FoodItem> getFoodItemsByUserId(Long userId) {
        return foodItemRepository.findByUserId(userId);
    }

    public FoodItem saveFoodItem(FoodItem foodItem) {
        return foodItemRepository.save(foodItem);
    }

    public FoodItem updateFoodItem(Long id, FoodItem foodItem) {
        foodItem.setId(id);
        return foodItemRepository.save(foodItem);
    }

    public void deleteFoodItem(Long id) {
        foodItemRepository.deleteById(id);
    }
}

