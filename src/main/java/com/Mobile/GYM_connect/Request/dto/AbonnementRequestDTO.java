// Dans un nouveau package, par ex: com.Mobile.GYM_connect.dto
package com.Mobile.GYM_connect.Request.dto;

import com.Mobile.GYM_connect.Enums.StatutAbonnement;
import java.math.BigDecimal;
import java.time.LocalDate;

// Ce DTO ne contient que les champs que le client doit fournir.
public class AbonnementRequestDTO {

    private LocalDate dateDebut;
    private LocalDate dateFin;
    private StatutAbonnement statut;
    private Long typeAbonnementId;






    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public StatutAbonnement getStatut() {
        return statut;
    }

    public void setStatut(StatutAbonnement statut) {
        this.statut = statut;
    }

    public Long getTypeAbonnementId() {
        return typeAbonnementId;
    }

    public void setTypeAbonnementId(Long typeAbonnementId) {
        this.typeAbonnementId = typeAbonnementId;
    }


    // Getters et Setters pour tous les champs...
}