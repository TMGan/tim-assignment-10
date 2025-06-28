package com.coderscampus.service;

import java.net.URI;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.coderscampus.Spoonacular.dto.DayResponse;
import com.coderscampus.Spoonacular.dto.WeekResponse;

@Service
public class SpoonacularService {
	private static final String API_KEY = "8b295766d3e94fffac8e53d273faa0a3"; 
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
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL)
                                  .queryParam("timeFrame", timeFrame)
                                  .queryParam("targetCalories", numCalories)
                                  .queryParam("diet", diet)
                                  .queryParam("exclude", exclusions)
                                  .queryParam("apiKey", API_KEY);
        
                                  if (numCalories != null && !numCalories.isEmpty()) {
                                      builder.queryParam("targetCalories", numCalories);
                                  }
                                  if (diet != null && !diet.isEmpty()) {
                                      builder.queryParam("diet", diet);
                                  }
                                  if (exclusions != null && !exclusions.isEmpty()) {
                                      builder.queryParam("exclude", exclusions);
                                  }

                                  return builder.build().toUri();
    }
}
