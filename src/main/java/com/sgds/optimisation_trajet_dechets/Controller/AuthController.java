package com.sgds.optimisation_trajet_dechets.Controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sgds.optimisation_trajet_dechets.Configuration.JwtUtils;
import com.sgds.optimisation_trajet_dechets.Model.Utilisateur;
import com.sgds.optimisation_trajet_dechets.Repository.UtilisateurRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/Auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final UtilisateurRepository utilisateurRepository; 
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;


    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Utilisateur utilisateur) {
        String hashedPassword = passwordEncoder.encode(utilisateur.getMotDePasse());

        if (utilisateurRepository.findByMotDePasse(hashedPassword) != null) {
            return ResponseEntity.badRequest().body("Error: Password is already taken!");
        }

        if (utilisateurRepository.findByEmail(utilisateur.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Error: Email is already in use!");
        }

        utilisateur.setLatitude(utilisateur.getLatitude());
        utilisateur.setLongitude(utilisateur.getLongitude());
        utilisateur.setMotDePasse(hashedPassword);

        Utilisateur savedUser = utilisateurRepository.save(utilisateur);
        return ResponseEntity.ok(savedUser);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Utilisateur utilisateur) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    utilisateur.getEmail(),
                    utilisateur.getMotDePasse()
                )
            );

            if (authentication.isAuthenticated()) {
                Map<String, Object> response = new HashMap<>();
                response.put("token", jwtUtils.generateToken(utilisateur.getEmail(), utilisateur.getMotDePasse()));
                response.put("type", "Bearer");
                return ResponseEntity.ok(response);
            }

            return ResponseEntity.status(401).body("Invalid credentials");

        } catch (AuthenticationException e) {
            log.error("Authentication failed: {}", e.getMessage());
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }
}
