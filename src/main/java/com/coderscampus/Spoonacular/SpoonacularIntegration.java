package com.coderscampus.Spoonacular;

import java.net.URI;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.coderscampus.Spoonacular.dto.DayResponse;

public class SpoonacularIntegration {

	@Test
	public void callApi() {
		System.out.println("Starting API test...");
		RestTemplate rt = new RestTemplate();
		try {
		URI uri = UriComponentsBuilder.fromUriString("https://api.spoonacular.com/mealplanner/generate")
													   .queryParam("timeFrame", "day")
													   .queryParam("targetCalories", "2000")
													   .queryParam("diet", "vegetarian")
													   .queryParam("exclude", "shellfish, olives")
													   .build()
													   .toUri();
		System.out.println("Request URI: "+uri);
		
		
		
		// Set API key in header
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-api-key", "ccfa8cb49d2f4716aaa657f9feff1713"); // Replace with your new key
        RequestEntity<Void> request = RequestEntity.get(uri)
                                                   .headers(headers)
                                                   .build();
		
		//Try fetching raw String response first
		ResponseEntity<String> rawResponse = rt.exchange(request, String.class);
		System.out.println("Raw API Response: "+ rawResponse.getBody());
		
		
		//Try mapping to DayResponse
		ResponseEntity<DayResponse> response = rt.exchange(request, DayResponse.class);
		System.out.println("Mapped Reponse: "+ response.getBody());
		} catch(RestClientException e) {
			System.err.println("API call failed: " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			System.err.println("Unexpected error: "+ e.getMessage());
			e.printStackTrace();
		}
		System.out.println("Test completed.");
		
			   
	}
	
}
