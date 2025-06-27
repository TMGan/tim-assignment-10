package com.coderscampus.service;

import java.net.URI;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.coderscampus.Spoonacular.dto.DayResponse;
import com.coderscampus.Spoonacular.dto.WeekResponse;

@Service
public class SpoonacularService {
	private static final String API_KEY = "ccfa8cb49d2f4716aaa657f9feff1713"; // Your API key
    private static final String BASE_URL = "https://api.spoonacular.com/mealplanner/generate";

    private final RestTemplate restTemplate;

    public SpoonacularService() {
        this.restTemplate = new RestTemplate();
    }

    public DayResponse getDailyMealPlan(String numCalories, String diet, String exclusions) {
        URI uri = buildUri("day", numCalories, diet, exclusions);
        return restTemplate.getForObject(uri, DayResponse.class);
    }

    public WeekResponse getWeeklyMealPlan(String numCalories, String diet, String exclusions) {
        URI uri = buildUri("week", numCalories, diet, exclusions);
        return restTemplate.getForObject(uri, WeekResponse.class);
    }

    private URI buildUri(String timeFrame, String numCalories, String diet, String exclusions) {
        return UriComponentsBuilder.fromUriString(BASE_URL)
                                  .queryParam("timeFrame", timeFrame)
                                  .queryParam("targetCalories", numCalories)
                                  .queryParam("diet", diet)
                                  .queryParam("exclude", exclusions)
                                  .queryParam("apiKey", API_KEY)
                                  .build()
                                  .toUri();
    }
}
