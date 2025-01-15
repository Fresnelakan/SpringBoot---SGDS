package com.sgds.optimisation_trajet_dechets.Service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;

@Service
public class TrajetService {
    private final RestTemplate restTemplate = new RestTemplate();
    private final String apiKey = "YOUR_GOOGLE_MAPS_API_KEY"; // Remplace par ta vraie clé API.

    public String calculerDistance(String origine, String destination) {
        try {
            String url = String.format(
                "https://maps.googleapis.com/maps/api/distancematrix/json?origins=%s&destinations=%s&key=%s",
                origine, destination, apiKey
            );
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            return response.getBody();
        } catch (Exception e) {
            return String.format("{\"error\":\"Une erreur s'est produite : %s\"}", e.getMessage());
        }
    }
}
