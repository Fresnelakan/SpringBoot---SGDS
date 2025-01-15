package com.sgds.optimisation_trajet_dechets.Service;


import com.sgds.optimisation_trajet_dechets.Model.Vehicule;
import com.sgds.optimisation_trajet_dechets.Repository.VehiculeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehiculeService {

    private final VehiculeRepository vehiculeRepository;

    public VehiculeService(VehiculeRepository vehiculeRepository) {
        this.vehiculeRepository = vehiculeRepository;
    }

    public List<Vehicule> obtenirTousLesVehicules() {
        return vehiculeRepository.findAll();
    }

    public Optional<Vehicule> obtenirVehiculeParId(Integer id) {
        return vehiculeRepository.findById(id);
    }

    public Vehicule ajouterVehicule(Vehicule vehicule) {
        return vehiculeRepository.save(vehicule);
    }

    public void deleteById(Integer id) {
        vehiculeRepository.deleteById(id);
    }
}
