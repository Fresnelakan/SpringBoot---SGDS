package com.sgds.optimisation_trajet_dechets.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "adresse")
public class Adresse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String rue;

    private String ville;

    private String codePostal;

    private String pays;

    
}
