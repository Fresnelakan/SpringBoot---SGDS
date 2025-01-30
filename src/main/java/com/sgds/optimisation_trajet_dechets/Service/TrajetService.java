package com.sgds.optimisation_trajet_dechets.Service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import com.sgds.optimisation_trajet_dechets.Model.Utilisateur;
import com.sgds.optimisation_trajet_dechets.Model.Vehicule;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TrajetService {
    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final VehiculeService vehiculeService;

    // URL de l'API OSRM pour le calcul d'itinéraire
    @Value("${graphhopper.api.key}")
    private String apiKey;

    @Value("${graphhopper.base.url}")
    private String graphhopperUrl;

    /**
     * Méthode pour calculer la distance et l'itinéraire entre deux coordonnées.
     */

     public String calculerTrajetOptimal(List<Utilisateur> souscripteurs) {
        System.out.println("[BACKEND] Appel à GraphHopper avec " + souscripteurs.size() + " points.");
        try {
            // Par défaut, utiliser un véhicule générique si aucun n'est trouvé
            String vehicleType = "car"; 
            
            // Si des véhicules existent, prendre le premier (à adapter selon la logique métier)
            List<Vehicule> vehicules = vehiculeService.obtenirTousLesVehicules();
            if (!vehicules.isEmpty()) {
                Vehicule vehicule = vehicules.get(0); // Déclaration unique de `vehicule`
                vehicleType = switch (vehicule.getType()) {
                    case Vehicule.Type.CAMION_A_BENNE -> "truck";
                    case Vehicule.Type.CLOBOTO -> "small_truck";
                    default -> "car";
                };
            }
    
            // Convertir les utilisateurs en chaîne de coordonnées
            String points = souscripteurs.stream()
                .map(u -> String.format("%s,%s", u.getLatitude(), u.getLongitude()))
                .collect(Collectors.joining("&point="));
    
            // Construire l'URL avec le type de véhicule
            String url = String.format("%s?point=%s&vehicle=%s&key=%s", 
                graphhopperUrl, 
                points,
                vehicleType, // Utilisation du type mappé
                apiKey);
    
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            
            // Vérifier la réponse de GraphHopper
            JsonNode root = objectMapper.readTree(response.getBody());
            if (!root.has("paths") || root.get("paths").isEmpty()) {
                return "{\"error\":\"Aucun trajet optimal trouvé par GraphHopper\"}";
            }
    
            JsonNode path = root.get("paths").get(0);
            return objectMapper.writeValueAsString(path);
    
        } catch (Exception e) {
            return String.format("{\"error\":\"%s\"}", e.getMessage());
        }
    }
}