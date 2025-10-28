// Dans le package com.Mobile.GYM_connect.Config
package com.Mobile.GYM_connect.Config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity // TRÈS IMPORTANT: Active les annotations comme @PreAuthorize
public class SecurityConfig { // Renommé de SecurityConfiguration pour correspondre à votre fichier

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Autowired
    public SecurityConfig(JwtAuthenticationFilter jwtAuthFilter,AuthenticationProvider authenticationProvider){
        this.jwtAuthFilter=jwtAuthFilter;
        this.authenticationProvider=authenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Désactiver CSRF pour les API REST
                .authorizeHttpRequests(auth -> auth
                        // Autoriser l'accès sans authentification à ces URLs
                        .requestMatchers(
                                "/api/auth/**", // Tous les endpoints sous /api/auth (login, register)
                                "/swagger-ui.html",
                                "/swagger-ui/**",
                                "/v3/api-docs/**"
                        ).permitAll()
                        // Pour toutes les autres requêtes, une authentification est requise
                        .anyRequest().authenticated()
                )
                // Configurer la gestion de session pour qu'elle soit STATELESS
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // Indiquer quel fournisseur d'authentification utiliser
                .authenticationProvider(authenticationProvider)
                // Ajouter notre filtre JWT avant le filtre de base de Spring Security
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}