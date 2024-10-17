package com.example.demo.Services;

import com.example.demo.Entity.FoodDetail;
import com.example.demo.Repository.FoodDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FoodDetailService {
    @Autowired FoodDetailRepository foodDetailRepository;

    public FoodDetail getFoodDetailByuserId(int userId) {
        return (FoodDetail) foodDetailRepository.findByUserId(userId);

    }
    public FoodDetail saveFoodDetail(FoodDetail foodDetail) {
        int userId = foodDetail.getUserId();
        FoodDetail foodDetail1 = (FoodDetail) foodDetailRepository.findByUserId(userId);
        if (foodDetail1 != null) {
            FoodDetail foodDetail2 = foodDetail;
            foodDetail2.setCarbohydrates(foodDetail1.getCarbohydrates()+foodDetail.getCarbohydrates());
            foodDetail2.setFat(foodDetail2.getFat()+foodDetail.getFat());
            foodDetail2.setProtein(foodDetail2.getProtein()+foodDetail.getProtein());
            return foodDetailRepository.save(foodDetail2);
        }
        else{
            return  foodDetailRepository.save(foodDetail1);
        }

    }

}
