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

    // URL de l'API OSRM pour le calcul d'itinéraire
    private final String osrmUrl = "http://router.project-osrm.org/route/v1/driving/";

    /**
     * Méthode pour calculer la distance et l'itinéraire entre deux coordonnées.
     */
    public String calculerTrajetOptimal(double originLat, double originLon, double destLat, double destLon) {
        try {
            // Formater les coordonnées pour OSRM (format: lon,lat;lon,lat)
            String coordinates = String.format("%s,%s;%s,%s", 
                originLon, originLat, 
                destLon, destLat
            );

            String url = osrmUrl + coordinates + "?overview=full";
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

            // Extraire la distance et la durée de la réponse
            JsonNode root = objectMapper.readTree(response.getBody());
            JsonNode route = root.get("routes").get(0);
            
            return String.format(
                "{\"distance\": %.2f km, \"duree\": %.2f minutes}", 
                route.get("distance").asDouble() / 1000, 
                route.get("duration").asDouble() / 60
            );

        } catch (Exception e) {
            return String.format("{\"error\":\"Erreur lors du calcul du trajet: %s\"}", e.getMessage());
        }
    }
}