package com.Mobile.GYM_connect.Controller; // Adaptez le package si besoin

import com.Mobile.GYM_connect.Models.Abonnement;
import com.Mobile.GYM_connect.Repository.AbonnementRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/abonnements")
@Tag(name = "Gestion des Abonnements", description = "API pour les opérations CRUD sur les abonnements")
public class AbonnementController {

    @Autowired
    private AbonnementRepository abonnementRepository;


    @PostMapping("ajouterAbonnement")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Créer un nouvel abonnement", description = "Ajoute un nouvel abonnement à la base de données.")
    @ApiResponse(responseCode = "201", description = "Abonnement créé avec succès",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Abonnement.class)) })
    public Abonnement createAbonnement(@RequestBody Abonnement abonnement) {
        return abonnementRepository.save(abonnement);
    }


    @GetMapping("all")
    @Operation(summary = "Récupérer la liste de tous les abonnements")
    public List<Abonnement> getAllAbonnements() {
        return abonnementRepository.findAll();
    }
}