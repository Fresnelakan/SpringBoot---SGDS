package com.sgds.optimisation_trajet_dechets.Controller;

import com.sgds.optimisation_trajet_dechets.Service.TrajetService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import com.sgds.optimisation_trajet_dechets.Model.Utilisateur;
import com.sgds.optimisation_trajet_dechets.Service.UtilisateurService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;



@RestController
@CrossOrigin(origins = "http://localhost:9000", allowedHeaders = "true", methods = {RequestMethod.GET, RequestMethod.POST}) // Ajoutez cette annotation
@RequestMapping("/api/trajets")
public class TrajetController {

    private final TrajetService trajetService;
    private final UtilisateurService utilisateurService;

    public TrajetController(TrajetService trajetService, UtilisateurService utilisateurService) {
        this.trajetService = trajetService;
        this.utilisateurService = utilisateurService;
    }

    // Modifier le endpoint comme ceci :
    @GetMapping("/optimiser")
    public ResponseEntity<String> optimiserTrajet(@RequestHeader("Authorization") String token) {
        System.out.println("[BACKEND] Requête reçue. Token : " + token);
        List<Utilisateur> souscripteurs = utilisateurService.getUsersByRole("SOUSCRIPTEUR");
        String result = trajetService.calculerTrajetOptimal(souscripteurs);
        return ResponseEntity.ok(result);
    }
}