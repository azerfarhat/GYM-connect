package com.Mobile.GYM_connect.Service;

import com.Mobile.GYM_connect.Enums.Role;
import com.Mobile.GYM_connect.Models.Client;
import com.Mobile.GYM_connect.Repository.ClientRepository;
import com.Mobile.GYM_connect.Response.dto.LoginResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Autowired
    public ClientService(ClientRepository clientRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.clientRepository = clientRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public Client registerClient(Client client) {
        Optional<Client> existingClient = clientRepository.findByEmail(client.getEmail());
        if (existingClient.isPresent()) {
            throw new IllegalStateException("L'adresse email est déjà utilisée.");
        }

        // Hachage du mot de passe avant de le sauvegarder
        String hashedPassword = passwordEncoder.encode(client.getMotDePasse());
        client.setMotDePasse(hashedPassword);

        client.setRole(Role.CLIENT);

        return clientRepository.save(client);
    }


    public LoginResponseDto loginClient(String email, String password) {
        // 1. Recherche du client par email
        Client client = clientRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Aucun utilisateur trouvé avec cet email : " + email));

        // 2. Vérification de la correspondance du mot de passe
        if (!passwordEncoder.matches(password, client.getMotDePasse())) {
            throw new BadCredentialsException("Mot de passe incorrect.");
        }

        // 3. Génération du token JWT
        String token = jwtService.generateToken(client);

        // 4. Création et retour de la réponse
        return new LoginResponseDto(token);
    }
}