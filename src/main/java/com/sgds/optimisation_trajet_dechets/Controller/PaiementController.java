package com.sgds.optimisation_trajet_dechets.Controller;


import com.sgds.optimisation_trajet_dechets.Model.Paiement;
import com.sgds.optimisation_trajet_dechets.Service.PaiementService;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/paiements")
public class PaiementController {

    private final PaiementService paiementService;


    public PaiementController(PaiementService paiementService) {
        this.paiementService = paiementService;
    }

    // Ajouter un paiement
    @PostMapping
    public Paiement effectuerPaiement(@RequestBody Paiement paiement) {
        return paiementService.effectuerPaiement(paiement);
    }

    // Obtenir tous les paiements
    @GetMapping
    public List<Paiement> obtenirTousLesPaiements() {
        return paiementService.obtenirTousLesPaiements();
    }

    // Obtenir un paiement par ID
    @GetMapping("/{id}")
    public Optional<Paiement> obtenirPaiementParId(@PathVariable Integer id) {
        return paiementService.obtenirPaiementParId(id);
    }
}


