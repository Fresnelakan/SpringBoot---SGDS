package com.sgds.optimisation_trajet_dechets.Service;

import com.sgds.optimisation_trajet_dechets.Model.Paiement;
import com.sgds.optimisation_trajet_dechets.Repository.PaiementRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaiementService {

    private final PaiementRepository paiementRepository;

    public PaiementService(PaiementRepository paiementRepository) {
        this.paiementRepository = paiementRepository;
    }

    // Effectuer un paiement (enregistrer un paiement)
    public Paiement effectuerPaiement(Paiement paiement) {
        return paiementRepository.save(paiement);
    }

    // Obtenir tous les paiements
    public List<Paiement> obtenirTousLesPaiements() {
        return paiementRepository.findAll();
    }

    // Obtenir un paiement par ID
    public Optional<Paiement> obtenirPaiementParId(Integer id) {
        return paiementRepository.findById(id);
    }

    // Supprimer un paiement
    public void supprimerPaiement(Integer id) {
        paiementRepository.deleteById(id);
    }
}
