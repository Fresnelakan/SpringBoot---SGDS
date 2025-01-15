package com.sgds.optimisation_trajet_dechets.Service;


import com.sgds.optimisation_trajet_dechets.Model.Notification;
import com.sgds.optimisation_trajet_dechets.Repository.NotificationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public List<Notification> obtenirToutesLesNotifications() {
        return notificationRepository.findAll();
    }

    public Optional<Notification> findById(Integer id) {
        return notificationRepository.findById(id);
    }

    public Notification envoyerNotification(Notification notification) {
        return notificationRepository.save(notification);
    }

    public void deleteById(Integer id) {
        notificationRepository.deleteById(id);
    }
}
