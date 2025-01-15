package com.sgds.optimisation_trajet_dechets.Service;


import com.sgds.optimisation_trajet_dechets.Model.Adresse;
import com.sgds.optimisation_trajet_dechets.Repository.AdresseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdresseService {

    private final AdresseRepository adresseRepository;

    public AdresseService(AdresseRepository adresseRepository) {
        this.adresseRepository = adresseRepository;
    }

    public List<Adresse> findAll() {
        return adresseRepository.findAll();
    }

    public Optional<Adresse> findById(Integer id) {
        return adresseRepository.findById(id);
    }

    public Adresse save(Adresse adresse) {
        return adresseRepository.save(adresse);
    }

    public void deleteById(Integer id) {
        adresseRepository.deleteById(id);
    }
}
