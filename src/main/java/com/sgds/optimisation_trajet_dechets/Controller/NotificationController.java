package com.sgds.optimisation_trajet_dechets.Controller;

import com.sgds.optimisation_trajet_dechets.Model.Notification;
import com.sgds.optimisation_trajet_dechets.Model.Utilisateur;
import com.sgds.optimisation_trajet_dechets.Repository.UtilisateurRepository;
import com.sgds.optimisation_trajet_dechets.Service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping
    public ResponseEntity<Notification> envoyerNotification(
        @RequestBody Notification notification,
        Authentication authentication
    ) {
        // Récupérer l'utilisateur connecté
        String email = authentication.getName();
        Utilisateur utilisateur = utilisateurRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        // Associer les données
        notification.setUtilisateur(utilisateur);
        notification.setDateNotification(LocalDateTime.now());

        return ResponseEntity.ok(notificationService.envoyerNotification(notification));
    }

    @GetMapping
    public List<Notification> obtenirToutesLesNotifications() {
        return notificationService.obtenirToutesLesNotifications();
    }
}