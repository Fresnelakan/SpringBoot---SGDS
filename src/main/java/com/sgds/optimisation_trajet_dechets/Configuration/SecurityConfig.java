package com.sgds.optimisation_trajet_dechets.Configuration;

import com.sgds.optimisation_trajet_dechets.Service.CustomUserDetailsService;
import com.sgds.optimisation_trajet_dechets.filter.JwtFilter;

import lombok.RequiredArgsConstructor;

import com.sgds.optimisation_trajet_dechets.Repository.UtilisateurRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

/*     private final UtilisateurRepository utilisateurRepository;
 */    private final CustomUserDetailsService customUserDetailsService;
    private final JwtUtils jwt;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/Auth/**").permitAll() // Permettre l'accès à l'authentification sans authentification
                .anyRequest().authenticated()
            )
            .addFilterBefore(new JwtFilter(customUserDetailsService, jwt), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    AuthenticationManager authenticationManager(HttpSecurity http, PasswordEncoder passwordEncoder) throws Exception{
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder);
        return authenticationManagerBuilder.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
/* 
    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService(utilisateurRepository);
    } */
}