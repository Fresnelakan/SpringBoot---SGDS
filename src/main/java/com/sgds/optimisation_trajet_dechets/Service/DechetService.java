package com.sgds.optimisation_trajet_dechets.Service;

import com.sgds.optimisation_trajet_dechets.Model.Service;
import com.sgds.optimisation_trajet_dechets.Repository.ServiceRepository;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service  // Utilisation du chemin complet pour éviter le conflit
public class DechetService {

    private final ServiceRepository serviceRepository;

    public DechetService(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    // Ajouter un service
    public Service ajouterService(Service service) {
        return serviceRepository.save(service);
    }

    // Obtenir la liste de tous les services
    public List<Service> obtenirTousLesServices() {
        return serviceRepository.findAll();
    }

    // Obtenir un service par son ID
    public Optional<Service> obtenirServiceParId(Long id) {
        return serviceRepository.findById(id);  // Utilisation de Long
    }

    // Mettre à jour un service
    public Service mettreAJourService(Long id, Service nouveauService) {
        return serviceRepository.findById(id).map(service -> {
            service.setNom(nouveauService.getNom());
            service.setDescription(nouveauService.getDescription());
            service.setPrix(nouveauService.getPrix());
            return serviceRepository.save(service);
        }).orElseThrow(() -> new RuntimeException("Service non trouvé pour l'ID : " + id));
    }

    // Supprimer un service
    public void supprimerService(Long id) {
        serviceRepository.deleteById(id);  // Utilisation de Long
    }
}
