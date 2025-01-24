package com.sgds.optimisation_trajet_dechets.Service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class TrajetService {
    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    // URL de l'API Nominatim pour le géocodage
    private final String nominatimUrl = "https://nominatim.openstreetmap.org/search?format=json&q=";

    // URL de l'API OSRM pour le calcul d'itinéraire
    private final String osrmUrl = "http://router.project-osrm.org/route/v1/driving/";

    /**
     * Méthode pour géocoder une adresse en coordonnées (latitude, longitude).
     */
    private String geocoderAdresse(String adresse) {
        try {
            String url = nominatimUrl + adresse.replace(" ", "+");
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

            // Parse la réponse JSON pour extraire la latitude et la longitude
            JsonNode root = objectMapper.readTree(response.getBody());
            if (root.isArray() && root.size() > 0) {
                JsonNode firstResult = root.get(0);
                double lat = firstResult.get("lat").asDouble();
                double lon = firstResult.get("lon").asDouble();
                return lon + "," + lat; // Format attendu par OSRM : "lon,lat"
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Méthode pour calculer la distance et l'itinéraire entre deux adresses.
     */
    public String calculerDistance(String origine, String destination) {
        try {
            // Géocodage des adresses
            String coordOrigine = geocoderAdresse(origine);
            String coordDestination = geocoderAdresse(destination);

            if (coordOrigine == null || coordDestination == null) {
                return "{\"error\":\"Impossible de géocoder les adresses.\"}";
            }

            // Calcul de l'itinéraire avec OSRM
            String url = osrmUrl + coordOrigine + ";" + coordDestination;
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

            // Retourne la réponse brute de l'API OSRM
            return response.getBody();
        } catch (Exception e) {
            return String.format("{\"error\":\"Une erreur s'est produite : %s\"}", e.getMessage());
        }
    }
}