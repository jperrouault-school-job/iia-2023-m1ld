package fr.formation.produitservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ProduitServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(ProduitServiceApplication.class, args);
	}
}
