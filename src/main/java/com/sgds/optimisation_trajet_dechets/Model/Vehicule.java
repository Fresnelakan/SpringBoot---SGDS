package com.sgds.optimisation_trajet_dechets.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "vehicules")
@Data
public class Vehicule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int capacite;

    
    public enum Type {
        CLOBOTO, CAMION_A_BENNE
    }

    
}
