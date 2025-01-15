package com.sgds.optimisation_trajet_dechets.Controller;

import com.sgds.optimisation_trajet_dechets.Model.Service;
import com.sgds.optimisation_trajet_dechets.Service.DechetService;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/services")
public class ServiceController {

    private final DechetService dechetService;


    public ServiceController(DechetService dechetService) {
        this.dechetService = dechetService;
    }

    // Ajouter un service
    @PostMapping
    public Service ajouterService(@RequestBody Service service) {
        return dechetService.ajouterService(service);
    }

    // Obtenir tous les services
    @GetMapping
    public List<Service> obtenirTousLesServices() {
        return dechetService.obtenirTousLesServices();
    }

    // Obtenir un service par ID
    @GetMapping("/{id}")
    public Optional<Service> obtenirServiceParId(@PathVariable Long id) {
        return dechetService.obtenirServiceParId(id);
    }

    // Mettre à jour un service
    @PutMapping("/{id}")
    public Service mettreAJourService(@PathVariable Long id, @RequestBody Service service) {
        return dechetService.mettreAJourService(id, service);
    }

    // Supprimer un service
    @DeleteMapping("/{id}")
    public void supprimerService(@PathVariable Long id) {
        dechetService.supprimerService(id);
    }
}
