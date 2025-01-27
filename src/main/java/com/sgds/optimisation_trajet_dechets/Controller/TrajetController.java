package com.sgds.optimisation_trajet_dechets.Controller;

import com.sgds.optimisation_trajet_dechets.Service.TrajetService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/trajets")
public class TrajetController {

    private final TrajetService trajetService;

    public TrajetController(TrajetService trajetService) {
        this.trajetService = trajetService;
    }

    // Dans TrajetController.java
@GetMapping("/optimiser")
public ResponseEntity<String> optimiserTrajet(
    @RequestParam double originLat, 
    @RequestParam double originLon,
    @RequestParam double destLat,
    @RequestParam double destLon
) {
    String result = trajetService.calculerTrajetOptimal(originLat, originLon, destLat, destLon);
    return ResponseEntity.ok(result);
}
}
