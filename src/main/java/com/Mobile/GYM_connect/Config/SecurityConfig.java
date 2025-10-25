package com.Mobile.GYM_connect.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity // Active la configuration de sécurité web de Spring
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 1. Désactiver CSRF (Cross-Site Request Forgery) car nous utilisons une API REST
                .csrf(csrf -> csrf.disable())

                // 2. Définir les règles d'autorisation pour les requêtes HTTP
                .authorizeHttpRequests(auth -> auth
                        // Autoriser l'accès sans authentification à ces URLs spécifiques
                        .requestMatchers(
                                "/api/auth/register", // Notre endpoint d'enregistrement
                                "/api/auth/login",
                                "/swagger-ui.html",      // La page principale de Swagger UI
                                "/swagger-ui/**",        // Les ressources de Swagger UI (CSS, JS, etc.)
                                "/v3/api-docs/**"        // La définition de l'API au format JSON que Swagger UI utilise
                        ).permitAll()
                        // Pour toutes les autres requêtes, une authentification est requise
                        .anyRequest().authenticated()
                )
                // 3. Activer la page de connexion par défaut (pour les routes qui restent protégées)
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}