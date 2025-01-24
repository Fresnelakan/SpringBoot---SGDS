document.addEventListener('DOMContentLoaded', function() {
    // Exemple de code pour récupérer les notifications pour l'agent
    fetch('/api/notifications')
        .then(response => response.json())
        .then(data => {
            const notificationsDiv = document.getElementById('notifications');
            notificationsDiv.innerHTML = JSON.stringify(data, null, 2);
        });

    // Exemple de code pour récupérer les adresses des souscripteurs et afficher sur la carte
    if (document.getElementById('map')) {
        const map = new google.maps.Map(document.getElementById('map'), {
            zoom: 10,
            center: { lat: 48.8566, lng: 2.3522 } // Paris par défaut
        });

        fetch('/api/utilisateurs')
            .then(response => response.json())
            .then(data => {
                data.forEach(utilisateur => {
                    const marker = new google.maps.Marker({
                        position: { lat: utilisateur.adresse.latitude, lng: utilisateur.adresse.longitude },
                        map: map,
                        title: utilisateur.nom
                    });
                });
            });
    }

    // Exemple de code pour envoyer une notification
    const notificationForm = document.getElementById('notificationForm');
    if (notificationForm) {
        notificationForm.addEventListener('submit', function(event) {
            event.preventDefault();
            const message = document.getElementById('message').value;

            fetch('/api/notifications', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({ message: message })
            })
            .then(response => response.json())
            .then(data => {
                alert('Notification envoyée');
            });
        });
    }

    // Exemple de code pour mettre à jour l'adresse
    const adresseForm = document.getElementById('adresseForm');
    if (adresseForm) {
        adresseForm.addEventListener('submit', function(event) {
            event.preventDefault();
            const rue = document.getElementById('rue').value;
            const ville = document.getElementById('ville').value;
            const codePostal = document.getElementById('codePostal').value;
            const pays = document.getElementById('pays').value;

            fetch('/api/utilisateurs/adresse', {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({ rue: rue, ville: ville, codePostal: codePostal, pays: pays })
            })
            .then(response => response.json())
            .then(data => {
                alert('Adresse mise à jour');
            });
        });
    }

    // Exemple de code pour démarrer le trajet optimisé
    const startRouteButton = document.getElementById('startRoute');
    if (startRouteButton) {
        startRouteButton.addEventListener('click', function() {
            fetch('/api/trajets/optimise')
                .then(response => response.json())
                .then(data => {
                    // Afficher le trajet optimisé sur la carte
                    const directionsService = new google.maps.DirectionsService();
                    const directionsRenderer = new google.maps.DirectionsRenderer();
                    directionsRenderer.setMap(map);

                    const waypoints = data.waypoints.map(point => ({
                        location: new google.maps.LatLng(point.latitude, point.longitude),
                        stopover: true
                    }));

                    const request = {
                        origin: new google.maps.LatLng(data.origin.latitude, data.origin.longitude),
                        destination: new google.maps.LatLng(data.destination.latitude, data.destination.longitude),
                        waypoints: waypoints,
                        travelMode: 'DRIVING'
                    };

                    directionsService.route(request, function(result, status) {
                        if (status === 'OK') {
                            directionsRenderer.setDirections(result);
                        }
                    });
                });
        });
    }
});