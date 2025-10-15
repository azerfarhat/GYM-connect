package com.Mobile.GYM_connect.repository; // Adaptez le package si besoin

import com.Mobile.GYM_connect.Models.Abonnement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AbonnementRepository extends JpaRepository<Abonnement, Long> {

}