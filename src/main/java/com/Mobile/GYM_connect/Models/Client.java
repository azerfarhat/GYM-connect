package com.Mobile.GYM_connect.Models;

import com.Mobile.GYM_connect.Enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "clients")
@Getter
@Setter
public class Client implements UserDetails { // <-- Implémentation de UserDetails

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String prenom;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String motDePasse;

    private String telephone;

    @Enumerated(EnumType.STRING) // <-- Stocke le rôle comme une chaîne (ex: "ADMIN")
    private Role role; // <-- Changé de String à l'énumération Role

    private String sexe;
    private Integer age;
    private Double poids;
    private Double taille;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Abonnement> abonnements = new ArrayList<>();

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Reservation> reservation = new ArrayList<>();

    public Client() {
    }

    // --- Implémentation des méthodes de UserDetails ---

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Retourne une liste contenant le rôle de l'utilisateur. C'est ici que Spring Security vérifie les autorisations.
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        // Spring Security utilisera cette méthode pour obtenir le mot de passe.
        return motDePasse;
    }

    @Override
    public String getUsername() {
        // Nous utilisons l'email comme "username".
        return email;
    }

    public Role getRole() {
        return role;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true; // Ou logique personnalisée
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Ou logique personnalisée
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Ou logique personnalisée
    }

    @Override
    public boolean isEnabled() {
        return true; // Ou logique personnalisée
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setRole(Role role) {
        this.role = role;
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