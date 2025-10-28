// Dans le package com.Mobile.GYM_connect.Config
package com.Mobile.GYM_connect.Config;

import com.Mobile.GYM_connect.Repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class ApplicationConfig {

    private final ClientRepository clientRepository;

    @Autowired
    public ApplicationConfig(ClientRepository clientRepository){
        this.clientRepository=clientRepository;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        // Indique à Spring Security comment charger un utilisateur par son email
        return username -> clientRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Client non trouvé avec l'email: " + username));
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        // Le "fournisseur" d'authentification qui utilise notre UserDetailsService et notre encodeur de mot de passe.
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder()); // Utilise le même PasswordEncoder que dans SecurityConfig
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}