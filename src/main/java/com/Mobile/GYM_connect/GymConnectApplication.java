package com.Mobile.GYM_connect;

import org.springframework.boot.context.properties.EnableConfigurationProperties; // Importer l'annotation
import com.Mobile.GYM_connect.Config.JwtProperties; // Importer la classe
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableConfigurationProperties(JwtProperties.class) // Activer la lecture des propriétés
public class GymConnectApplication {

	public static void main(String[] args) {
		SpringApplication.run(GymConnectApplication.class, args);
	}

}
