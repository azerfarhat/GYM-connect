package com.Mobile.GYM_connect.Controller;

import com.Mobile.GYM_connect.Models.TypeAbonnement;
import com.Mobile.GYM_connect.Repository.TypeAbonnementRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/types-abonnement")
@Tag(name = "Gestion des Types d'Abonnement", description = "API pour les opérations CRUD sur les types d'abonnement (Admin seulement)")
public class TypeAbonnementController {

    @Autowired
    private TypeAbonnementRepository typeAbonnementRepository;

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<TypeAbonnement> createTypeAbonnement(@RequestBody TypeAbonnement typeAbonnement) {
        TypeAbonnement savedType = typeAbonnementRepository.save(typeAbonnement);
        return new ResponseEntity<>(savedType, HttpStatus.CREATED);
    }

    @GetMapping
    public List<TypeAbonnement> getAllTypesAbonnement() {
        return typeAbonnementRepository.findAll();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> deleteTypeAbonnement(@PathVariable Long id) {
        if (!typeAbonnementRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        typeAbonnementRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    //na9sa fonciton modifier type abonnement
}