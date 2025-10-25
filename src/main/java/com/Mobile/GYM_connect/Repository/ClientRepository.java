package com.Mobile.GYM_connect.Repository;

import com.Mobile.GYM_connect.Models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    /**
     * Recherche un client par son adresse email.
     * Spring Data JPA génère automatiquement la requête SQL à partir du nom de la méthode.
     * @param email L'email à rechercher.
     * @return un Optional contenant le Client s'il est trouvé, sinon un Optional vide.
     */
    Optional<Client> findByEmail(String email);
}