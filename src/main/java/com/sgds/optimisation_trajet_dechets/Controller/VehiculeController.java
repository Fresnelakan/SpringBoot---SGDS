package com.sgds.optimisation_trajet_dechets.Controller;


import com.sgds.optimisation_trajet_dechets.Model.Vehicule;
import com.sgds.optimisation_trajet_dechets.Service.VehiculeService;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/vehicules")
public class VehiculeController {

    private final VehiculeService vehiculeService;

    public VehiculeController(VehiculeService vehiculeService) {
        this.vehiculeService = vehiculeService;
    }

    // Ajouter un véhicule
    @PostMapping
    public Vehicule ajouterVehicule(@RequestBody Vehicule vehicule) {
        return vehiculeService.ajouterVehicule(vehicule);
    }

    // Obtenir tous les véhicules
    @GetMapping
    public List<Vehicule> obtenirTousLesVehicules() {
        return vehiculeService.obtenirTousLesVehicules();
    }

    // Obtenir un véhicule par ID
    @GetMapping("/{id}")
    public Optional<Vehicule> obtenirVehiculeParId(@PathVariable Integer id) {
        return vehiculeService.obtenirVehiculeParId(id);
    }
}
