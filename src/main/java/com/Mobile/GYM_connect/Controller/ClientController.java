package com.Mobile.GYM_connect.Controller;

import com.Mobile.GYM_connect.Models.Client;
import com.Mobile.GYM_connect.Request.dto.LoginRequestDto;
import com.Mobile.GYM_connect.Request.dto.RegisterRequestDto;
import com.Mobile.GYM_connect.Response.dto.LoginResponseDto;
import com.Mobile.GYM_connect.Response.dto.RegisterResponseDto;
import com.Mobile.GYM_connect.Service.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth") // Préfixe pour toutes les routes de ce contrôleur
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerClient(@Valid @RequestBody RegisterRequestDto requestDto) {
        try {
            Client newClient = new Client();
            newClient.setNom(requestDto.getNom());
            newClient.setPrenom(requestDto.getPrenom());
            newClient.setEmail(requestDto.getEmail());
            newClient.setMotDePasse(requestDto.getMotDePasse());
            newClient.setTelephone(requestDto.getTelephone());
            newClient.setSexe(requestDto.getSexe());
            newClient.setAge(requestDto.getAge());
            newClient.setPoids(requestDto.getPoids());
            newClient.setTaille(requestDto.getTaille());

            Client savedClient = clientService.registerClient(newClient);

            RegisterResponseDto response = new RegisterResponseDto(
                    savedClient.getEmail(),
                    "Client créé avec succès."
            );

            return new ResponseEntity<>(response, HttpStatus.CREATED);

        } catch (IllegalStateException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity<>("Une erreur est survenue lors de l'inscription.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginClient(@RequestBody LoginRequestDto requestDto) {
        try {
            LoginResponseDto response = clientService.loginClient(requestDto.getEmail(), requestDto.getMotDePasse());
            return ResponseEntity.ok(response); // 200 OK

        } catch (UsernameNotFoundException | BadCredentialsException e) {
            return new ResponseEntity<>("Email ou mot de passe invalide.", HttpStatus.UNAUTHORIZED); // 401 Unauthorized
        } catch (Exception e) {
            return new ResponseEntity<>("Une erreur est survenue lors de la connexion.", HttpStatus.INTERNAL_SERVER_ERROR); // 500
        }
    }
}