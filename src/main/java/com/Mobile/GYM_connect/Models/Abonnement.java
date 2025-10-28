package com.Mobile.GYM_connect.Models; // Adaptez le package si besoin

import com.Mobile.GYM_connect.Enums.StatutAbonnement;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Schema(description = "Représente un abonnement souscrit par un membre.") // Description pour la classe entière
@Table(name = "abonnement") // Nom de la table
public class Abonnement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identifiant unique de l'abonnement, généré automatiquement.", accessMode = Schema.AccessMode.READ_ONLY) // En lecture seule
    private Long id;


    @NotNull
    @FutureOrPresent // La date doit être aujourd'hui ou dans le futur
    @Schema(description = "Date de début de validité de l'abonnement.", example = "2024-10-21")
    private LocalDate dateDebut;

    @NotNull
    @FutureOrPresent // La date doit être aujourd'hui ou dans le futur
    @Schema(description = "Date de fin de validité de l'abonnement.", example = "2024-11-20")
    private LocalDate dateFin;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    @Schema(description = "Statut actuel de l'abonnement.", example = "ACTIF")
    private StatutAbonnement statut;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "abonnements", "reservation"})
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_abonnement_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) // Pour bien gérer la conversion JSON avec LAZY loading
    private TypeAbonnement typeAbonnement;



    public Abonnement() {}

    public TypeAbonnement getTypeAbonnement() {
        return typeAbonnement;
    }

    public void setTypeAbonnement(TypeAbonnement typeAbonnement) {
        this.typeAbonnement = typeAbonnement;
    }


    public void setClient(Client client) {
        this.client = client;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StatutAbonnement getStatut() {
        return statut;
    }

    public void setStatut(StatutAbonnement statut) {
        this.statut = statut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }


    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }
}