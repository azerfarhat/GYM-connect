package com.Mobile.GYM_connect.Service;

import com.Mobile.GYM_connect.Config.JwtProperties;
import com.Mobile.GYM_connect.Models.Client;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {

    // 1. Injecter la classe de configuration au lieu de valeurs en dur
    private final JwtProperties jwtProperties;

    public JwtService(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }

    public String generateToken(Client client) {
        return generateToken(new HashMap<>(), client);
    }

    public String generateToken(Map<String, Object> extraClaims, Client client) {
        return Jwts.builder()
                .setClaims(extraClaims) // Ajoute des informations supplémentaires si nécessaire
                .setSubject(client.getEmail()) // Le "sujet" du token (généralement l'username/email)
                .setIssuedAt(new Date(System.currentTimeMillis())) // Date de création
                .setExpiration(new Date(System.currentTimeMillis() + jwtProperties.getExpirationMs())) // Date d'expiration lue depuis les propriétés
                .signWith(getSignInKey(), SignatureAlgorithm.HS256) // Signature avec la clé secrète
                .compact(); // Construit le token sous forme de chaîne
    }

    // Méthode pour préparer la clé de signature à partir de la clé secrète en Base64
    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtProperties.getSecretKey());
        return Keys.hmacShaKeyFor(keyBytes);
    }
}