package com.example.demo.Controller;

import com.example.demo.Entity.FoodSearch;
import com.example.demo.Services.FoodServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "https://fitnessproject-production.up.railway.app")
public class FoodController {

    @Autowired
    private FoodServices foodServices;

    @GetMapping("/api/search")
    public List<FoodSearch> searchFood(@RequestParam String name) {
        System.out.println("Received search request for name: " + name);

        // Split search terms if needed (for example, by comma)
        String[] terms = name.split(",");

        // Perform search (this is a simple example, adjust as needed)
        List<FoodSearch> results = new ArrayList<>();
        for (String term : terms) {
            results.addAll(foodServices.searchFood(term.trim()));
        }

        // Deduplicate results if necessary
        return results.stream().distinct().collect(Collectors.toList());
    }

}
