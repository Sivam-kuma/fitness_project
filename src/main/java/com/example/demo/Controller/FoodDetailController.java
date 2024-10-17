package com.example.demo.Controller;

import com.example.demo.Entity.FoodDetail;
import com.example.demo.Services.FoodDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/Food")
public class FoodDetailController {
    @Autowired
    FoodDetailService foodDetailService;

    @PostMapping("/save")
    public ResponseEntity<?> saveFood (@RequestBody FoodDetail foodDetail) {
      try{
          FoodDetail savedFood=foodDetailService.saveFoodDetail(foodDetail);
          return ResponseEntity.ok(savedFood);
      }
      catch(Exception e){
          return ResponseEntity.badRequest().body(e.getMessage());
      }

    }
    @GetMapping("/get/{userId}")
    public ResponseEntity<?> getFood (@PathVariable int userId) {
        try{
           FoodDetail getFood = foodDetailService.getFoodDetailByuserId(userId);
           return ResponseEntity.ok(getFood);
        }
        catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
