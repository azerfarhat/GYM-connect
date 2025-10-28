package com.Mobile.GYM_connect.Controller;

import com.Mobile.GYM_connect.Models.Abonnement;
import com.Mobile.GYM_connect.Models.Client;
import com.Mobile.GYM_connect.Models.TypeAbonnement;
import com.Mobile.GYM_connect.Repository.AbonnementRepository;
import com.Mobile.GYM_connect.Repository.ClientRepository;
import com.Mobile.GYM_connect.Repository.TypeAbonnementRepository; // Importez le nouveau repository
import com.Mobile.GYM_connect.Request.dto.AbonnementRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api")
@Tag(name = "Gestion des Abonnements", description = "API pour les opérations CRUD sur les abonnements")
public class AbonnementController {

    @Autowired
    private AbonnementRepository abonnementRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private TypeAbonnementRepository typeAbonnementRepository; // Injection du nouveau repository

    @PostMapping("/clients/{clientId}/abonnements")
    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(summary = "Créer un abonnement pour un client à partir d'un type (Admin seulement)",
            description = "Ajoute un nouvel abonnement et l'associe à un client et un type d'abonnement existants.")
    public ResponseEntity<Abonnement> createAbonnement(
            @PathVariable Long clientId,
            @RequestBody AbonnementRequestDTO dto) {

        // 1. Trouver le client
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client non trouvé avec l'ID: " + clientId));

        // 2. Trouver le type d'abonnement
        TypeAbonnement typeAbonnement = typeAbonnementRepository.findById(dto.getTypeAbonnementId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Type d'abonnement non trouvé avec l'ID: " + dto.getTypeAbonnementId()));

        // 3. Créer le nouvel abonnement et lier les entités
        Abonnement newAbonnement = new Abonnement();
        newAbonnement.setClient(client);
        newAbonnement.setTypeAbonnement(typeAbonnement);
        newAbonnement.setDateDebut(dto.getDateDebut());
        newAbonnement.setDateFin(dto.getDateFin());
        newAbonnement.setStatut(dto.getStatut());

        // 4. Sauvegarder
        Abonnement savedAbonnement = abonnementRepository.save(newAbonnement);

        return new ResponseEntity<>(savedAbonnement, HttpStatus.CREATED);
    }

    @GetMapping("/abonnements/all")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Abonnement> getAllAbonnements() {
        return abonnementRepository.findAll();
    }
}