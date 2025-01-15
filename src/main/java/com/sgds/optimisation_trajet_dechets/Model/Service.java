package com.sgds.optimisation_trajet_dechets.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "service")
@Data
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // ID en Long

    private String nom;
    private String description;
    private double prix;

    
}

