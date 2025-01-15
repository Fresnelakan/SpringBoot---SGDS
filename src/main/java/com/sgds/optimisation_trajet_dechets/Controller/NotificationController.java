package com.sgds.optimisation_trajet_dechets.Controller;


import com.sgds.optimisation_trajet_dechets.Model.Notification;
import com.sgds.optimisation_trajet_dechets.Service.NotificationService;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    // Envoyer une notification
    @PostMapping
    public Notification envoyerNotification(@RequestBody Notification notification) {
        return notificationService.envoyerNotification(notification);
    }

    // Obtenir toutes les notifications
    @GetMapping
    public List<Notification> obtenirToutesLesNotifications() {
        return notificationService.obtenirToutesLesNotifications();
    }
}
