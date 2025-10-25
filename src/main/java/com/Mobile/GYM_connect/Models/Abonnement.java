package com.Mobile.GYM_connect.Models; // Adaptez le package si besoin

import com.Mobile.GYM_connect.Enums.StatutAbonnement;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @NotBlank // Indique que ce champ ne peut pas être vide ou nul (validation + Swagger)
    @Column(nullable = false, length = 50)
    @Schema(description = "Type de l'abonnement.", example = "Mensuel Classique")
    private String type;

    @NotNull // Doit être fourni
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

    @NotNull // Doit être fourni
    @PositiveOrZero // Le montant ne peut pas être négatif
    @Column(precision = 10, scale = 2)
    @Schema(description = "Montant total payé pour l'abonnement.", example = "29.99")
    private BigDecimal montant;

    @PositiveOrZero // La remise ne peut pas être négative
    @Column(precision = 10, scale = 2)
    @Schema(description = "Montant de la remise appliquée (si applicable).", example = "5.00")
    private BigDecimal remis; // Peut-être "remise" ?

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;



    public Abonnement() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getRemis() {
        return remis;
    }

    public void setRemis(BigDecimal remis) {
        this.remis = remis;
    }

    public BigDecimal getMontant() {
        return montant;
    }

    public void setMontant(BigDecimal montant) {
        this.montant = montant;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }
}