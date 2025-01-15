package com.sgds.optimisation_trajet_dechets.Service;

import com.sgds.optimisation_trajet_dechets.Model.Utilisateur;
import com.sgds.optimisation_trajet_dechets.Repository.UtilisateurRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;

    public UtilisateurService(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    // Ajouter un utilisateur (même fonction que save)
    public Utilisateur ajouterUtilisateur(Utilisateur utilisateur) {
        return utilisateurRepository.save(utilisateur);
    }

    // Obtenir tous les utilisateurs
    public List<Utilisateur> obtenirTousLesUtilisateurs() {
        return utilisateurRepository.findAll();
    }

    // Obtenir un utilisateur par son ID
    public Optional<Utilisateur> obtenirUtilisateurParId(Long id) {
        return utilisateurRepository.findById(id);
    }

    // Mettre à jour un utilisateur
    public Utilisateur mettreAJourUtilisateur(Long id, Utilisateur utilisateur) {
        return utilisateurRepository.findById(id).map(u -> {
            u.setNom(utilisateur.getNom());
            u.setEmail(utilisateur.getEmail());
            u.setMotDePasse(utilisateur.getMotDePasse());
            return utilisateurRepository.save(u);
        }).orElseThrow(() -> new RuntimeException("Utilisateur non trouvé pour l'ID : " + id));
    }

    // Supprimer un utilisateur
    public void supprimerUtilisateur(Long id) {
        utilisateurRepository.deleteById(id);
    }
}
