package com.example.demo.Controller;




import com.example.demo.Entity.FoodItem;

import com.example.demo.Services.FoodItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fooditems")
public class FoodItemController {


    @Autowired
    FoodItemService foodItemService;

    @GetMapping
    public List<FoodItem> getAllFoodItems() {
        return foodItemService.getAllFoodItems();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FoodItem> getFoodItemById(@PathVariable Long id) {
        FoodItem foodItem = foodItemService.getFoodItemById(id);
        return foodItem != null ? ResponseEntity.ok(foodItem) : ResponseEntity.notFound().build();
    }

    @GetMapping("/user/{userId}")
    public List<FoodItem> getFoodItemsByUserId(@PathVariable Long userId) {
        return foodItemService.getFoodItemsByUserId(userId);
    }

    @PostMapping
    public FoodItem saveFoodItem(@RequestBody FoodItem foodItem) {
        return foodItemService.saveFoodItem(foodItem);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FoodItem> updateFoodItem(@PathVariable Long id, @RequestBody FoodItem foodItem) {
        FoodItem updatedItem = foodItemService.updateFoodItem(id, foodItem);
        return updatedItem != null ? ResponseEntity.ok(updatedItem) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFoodItem(@PathVariable Long id) {
        foodItemService.deleteFoodItem(id);
        return ResponseEntity.noContent().build();
    }
}
