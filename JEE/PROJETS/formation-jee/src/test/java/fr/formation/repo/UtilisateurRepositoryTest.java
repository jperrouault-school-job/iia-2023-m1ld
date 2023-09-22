package fr.formation.repo;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import fr.formation.model.Utilisateur;

@DataJpaTest
class UtilisateurRepositoryTest {
    @Autowired
    private UtilisateurRepository repo;
    
    @Test
    void shouldFindByUsernameWhenAdmin() {
        Optional<Utilisateur> optResult = this.repo.findByUsername("admin");

        Assertions.assertTrue(optResult.isPresent());
    }
    
    @Test
    void shouldNotFindByUsernameWhenPasAdmin() {
        Optional<Utilisateur> optResult = this.repo.findByUsername("pasadmin");

        Assertions.assertFalse(optResult.isPresent());
    }

}
