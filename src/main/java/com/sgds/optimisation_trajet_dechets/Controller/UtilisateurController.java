package com.sgds.optimisation_trajet_dechets.Controller;

import com.sgds.optimisation_trajet_dechets.Model.Utilisateur;
import com.sgds.optimisation_trajet_dechets.Model.Utilisateur.Role;
import com.sgds.optimisation_trajet_dechets.Model.Vehicule;
import com.sgds.optimisation_trajet_dechets.Service.UtilisateurService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/utilisateurs")
public class UtilisateurController {

    private final UtilisateurService utilisateurService;

    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    // Endpoint pour récupérer les clients avec coordonnées
    @GetMapping("/clients")
    public List<Utilisateur> getClientsWithCoordinates() {
    // Aucun changement si le rôle est correctement passé
    return utilisateurService.getUsersByRole("SOUSCRIPTEUR")
        .stream()
        .filter(u -> u.getLatitude() != null && u.getLongitude() != null)
        .collect(Collectors.toList());
        
}

    // Méthodes existantes conservées
    @PostMapping
    public Utilisateur ajouterUtilisateur(@RequestBody Utilisateur utilisateur) {
        return utilisateurService.ajouterUtilisateur(utilisateur);
    }

    @GetMapping
    public List<Utilisateur> obtenirTousLesUtilisateurs() {
        return utilisateurService.obtenirTousLesUtilisateurs();
    }

    @GetMapping("/{id}")
    public Optional<Utilisateur> obtenirUtilisateurParId(@PathVariable Long id) {
        return utilisateurService.obtenirUtilisateurParId(id);
    }

    @PutMapping("/{id}")
    public Utilisateur mettreAJourUtilisateur(@PathVariable Long id, @RequestBody Utilisateur utilisateur) {
        return utilisateurService.mettreAJourUtilisateur(id, utilisateur);
    }

    @DeleteMapping("/{id}")
    public void supprimerUtilisateur(@PathVariable Long id) {
        utilisateurService.supprimerUtilisateur(id);
    }

    @PostMapping("/{id}/assigner-vehicule")
    public ResponseEntity<String> assignerVehicule(
        @PathVariable Long id, 
        @RequestBody Vehicule vehicule
    ) {
        Optional<Utilisateur> userOpt = utilisateurService.obtenirUtilisateurParId(id);
        if (userOpt.isEmpty() || !userOpt.get().getRole().equals(Role.AGENT)) {
            return ResponseEntity.badRequest().body("Agent non trouvé");
        }

        Utilisateur agent = userOpt.get();
        agent.setVehicule(vehicule);
        utilisateurService.mettreAJourUtilisateur(id, agent);
        
        return ResponseEntity.ok("Véhicule assigné avec succès");
    }
}