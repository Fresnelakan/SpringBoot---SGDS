package com.sgds.optimisation_trajet_dechets.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sgds.optimisation_trajet_dechets.Model.Utilisateur;
import com.sgds.optimisation_trajet_dechets.Repository.UtilisateurRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    private final String secretKey = "WXIeT+NSgZurQ8G6P06jCJZqiiAdgxYtWyKcHqLL7Ts=";
    


    @GetMapping("/infos")
    public ResponseEntity<?> getUserInfos(@RequestHeader("Authorization") String token) {
        // Décoder le token
        String jwtToken = token.substring(7);

        Claims claims = Jwts.parserBuilder()
            .setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes()))
            .build()
            .parseClaimsJws(jwtToken)
            .getBody();

        // Extraire le subject du token
        String email = claims.get("sub", String.class);

        // Trouver le user par son email
        Optional<Utilisateur> user = utilisateurRepository.findByEmail(email);

        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.badRequest().body("User not found");
        }
    }
    
}
