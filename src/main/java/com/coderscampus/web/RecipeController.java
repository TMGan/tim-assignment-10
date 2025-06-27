package com.coderscampus.web;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.coderscampus.Spoonacular.dto.DayResponse;
import com.coderscampus.Spoonacular.dto.WeekResponse;
import com.coderscampus.service.SpoonacularService;

@RestController
public class RecipeController {

	private final SpoonacularService spoonacularService;

    public RecipeController(SpoonacularService spoonacularService) {
        this.spoonacularService = spoonacularService;
    }

    @GetMapping("mealplanner/day")
    public ResponseEntity<DayResponse> getDayMeals(@RequestParam String numCalories, @RequestParam String diet,@RequestParam String exclusions) {
        DayResponse dayResponse = spoonacularService.getDailyMealPlan(numCalories, diet, exclusions);
        return ResponseEntity.ok(dayResponse);
    }

    @GetMapping("mealplanner/week")
    public ResponseEntity<WeekResponse> getWeekMeals(@RequestParam String numCalories, @RequestParam String diet, @RequestParam String exclusions) {
        WeekResponse weekResponse = spoonacularService.getWeeklyMealPlan(numCalories, diet, exclusions);
        return ResponseEntity.ok(weekResponse);
    }
}
