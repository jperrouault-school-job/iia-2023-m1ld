package fr.formation.demoscreenshare.service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import fr.formation.demoscreenshare.model.Utilisateur;
import fr.formation.demoscreenshare.repo.UtilisateurRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class GenerateDataService implements CommandLineRunner {
    private final UtilisateurRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        Utilisateur user = new Utilisateur();
        Utilisateur admin = new Utilisateur();

        user.setUsername("user");
        user.setPassword(this.passwordEncoder.encode("123456$"));
        

        admin.setUsername("admin");
        admin.setPassword(this.passwordEncoder.encode("123456$"));

        this.repository.save(user);
        this.repository.save(admin);
    }
}
