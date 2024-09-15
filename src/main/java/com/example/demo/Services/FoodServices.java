package com.example.demo.Services;

import com.example.demo.Entity.FoodSearch;

import com.example.demo.Repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FoodServices {
    @Autowired
    private FoodRepository foodRepository;
    public List<FoodSearch> searchFood(String name) {
        System.out.println("Searching for: " + name); // Debug log
        List<FoodSearch> results = foodRepository.findByNameContainingIgnoreCase(name);
        System.out.println("Results found: " + results.size()); // Debug log
        return results;
    }

}
