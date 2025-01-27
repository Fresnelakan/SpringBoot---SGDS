package com.sgds.optimisation_trajet_dechets.Repository;

import com.sgds.optimisation_trajet_dechets.Model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
    Optional<Utilisateur> findByEmail(String email);
    Utilisateur findByNom(String nom);
    Utilisateur findByMotDePasse(String motDePasse);
    List<Utilisateur> findByRole(Utilisateur.Role role); // Résolu
}