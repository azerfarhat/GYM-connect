package com.Mobile.GYM_connect.Repository;

import com.Mobile.GYM_connect.Models.TypeAbonnement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeAbonnementRepository extends JpaRepository<TypeAbonnement, Long> {
}