package com.sgds.optimisation_trajet_dechets.Controller;


import com.sgds.optimisation_trajet_dechets.Model.Utilisateur;
import com.sgds.optimisation_trajet_dechets.Service.UtilisateurService;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/utilisateurs")
public class UtilisateurController {

    private final UtilisateurService utilisateurService;

    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    // Ajouter un utilisateur
    @PostMapping
    public Utilisateur ajouterUtilisateur(@RequestBody Utilisateur utilisateur) {
        return utilisateurService.ajouterUtilisateur(utilisateur);
    }

    // Obtenir tous les utilisateurs
    @GetMapping
    public List<Utilisateur> obtenirTousLesUtilisateurs() {
        return utilisateurService.obtenirTousLesUtilisateurs();
    }

    // Obtenir un utilisateur par ID
    @GetMapping("/{id}")
    public Optional<Utilisateur> obtenirUtilisateurParId(@PathVariable Long id) {
        return utilisateurService.obtenirUtilisateurParId(id);
    }

    // Mettre à jour un utilisateur
    @PutMapping("/{id}")
    public Utilisateur mettreAJourUtilisateur(@PathVariable Long id, @RequestBody Utilisateur utilisateur) {
        return utilisateurService.mettreAJourUtilisateur(id, utilisateur);
    }

    // Supprimer un utilisateur
    @DeleteMapping("/{id}")
    public void supprimerUtilisateur(@PathVariable Long id) {
        utilisateurService.supprimerUtilisateur(id);
    }
}
