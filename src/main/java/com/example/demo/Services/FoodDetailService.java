package com.example.demo.Services;

import com.example.demo.Entity.FoodDetail;
import com.example.demo.Repository.FoodDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FoodDetailService {
    @Autowired FoodDetailRepository foodDetailRepository;

    public FoodDetail getFoodDetailByuserId(int userId) {
        return  foodDetailRepository.findByUserId(userId);

    }
    public FoodDetail saveFoodDetail(FoodDetail foodDetail) {
        int userId = foodDetail.getUserId();
        FoodDetail foodDetail1 =  foodDetailRepository.findByUserId(userId);
        if (foodDetail1 != null) {

            foodDetail1.setCarbohydrates(foodDetail1.getCarbohydrates()+foodDetail.getCarbohydrates());
            foodDetail1.setFat(foodDetail1.getFat()+foodDetail.getFat());
            foodDetail1.setProtein(foodDetail1.getProtein()+foodDetail.getProtein());
            return foodDetailRepository.save(foodDetail1);
        }
        else{
            return  foodDetailRepository.save(foodDetail);
        }

    }

}
