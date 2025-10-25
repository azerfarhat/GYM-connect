package com.Mobile.GYM_connect.Request.dto;

import jakarta.validation.constraints.*;

public class RegisterRequestDto {

    @NotBlank(message = "Le nom ne peut pas être vide.")
    private String nom;

    @NotBlank(message = "Le prénom ne peut pas être vide.")
    private String prenom;

    @NotBlank(message = "L'email ne peut pas être vide.")
    @Email(message = "Le format de l'email est invalide.")
    private String email;

    @NotBlank(message = "Le mot de passe ne peut pas être vide.")
    @Size(min = 8, message = "Le mot de passe doit contenir au moins 8 caractères.")
    private String motDePasse;

    @NotBlank(message = "Le numero ne peut pas être vide.")
    private String telephone;

    @NotBlank(message = "Le sexe ne peut pas être vide.")
    private String sexe;

    @NotNull(message = "L'âge est obligatoire.")
    @Min(value = 14, message = "L'âge minimum est de 14 ans.")
    private Integer age;

    @NotNull(message = "Le poids est obligatoire.")
    @Positive(message = "Le poids doit être un nombre positif.")
    private Double poids;

    @NotNull(message = "La taille est obligatoire.")
    @Positive(message = "La taille doit être un nombre positif.")
    private Double taille;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getPoids() {
        return poids;
    }

    public void setPoids(Double poids) {
        this.poids = poids;
    }

    public Double getTaille() {
        return taille;
    }

    public void setTaille(Double taille) {
        this.taille = taille;
    }
}