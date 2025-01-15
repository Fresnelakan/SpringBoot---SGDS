package com.sgds.optimisation_trajet_dechets.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "paiement")
public class Paiement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur utilisateur;

    @ManyToOne
    @JoinColumn(name = "service_id")
    private Service service;

    private double montant;

    private LocalDateTime datePaiement;

    @Enumerated(EnumType.STRING)
    private TypeStatut typeStatut;

}
