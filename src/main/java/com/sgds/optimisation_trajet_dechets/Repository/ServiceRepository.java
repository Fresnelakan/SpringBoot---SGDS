package com.sgds.optimisation_trajet_dechets.Repository;


import com.sgds.optimisation_trajet_dechets.Model.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Long> {
}

