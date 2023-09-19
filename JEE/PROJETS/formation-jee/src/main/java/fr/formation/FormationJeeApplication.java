package fr.formation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FormationJeeApplication {
	public static void main(String[] args) throws Exception {
		SpringApplication.run(FormationJeeApplication.class, args);
	}

	// @Bean
	// public DemoContainer demo() {
	// 	return new DemoContainer();
	// }
}
