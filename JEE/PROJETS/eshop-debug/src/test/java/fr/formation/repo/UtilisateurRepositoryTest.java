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
    private UtilisateurRepository repoUtilisateur;
    
    @Test
    void shouldFindByUsernameReturnUser() {
        // given

        // when
        Optional<Utilisateur> optUtilisateur = this.repoUtilisateur.findByUsername("U1");

        // then
        Assertions.assertNotNull(optUtilisateur);
        Assertions.assertTrue(optUtilisateur.isPresent());
        Assertions.assertEquals("U1", optUtilisateur.get().getId());
    }
    
    @Test
    void shouldFindByUsernameReturnNoUser() {
        // given

        // when
        Optional<Utilisateur> optUtilisateur = this.repoUtilisateur.findByUsername("notexists");

        // then
        Assertions.assertNotNull(optUtilisateur);
        Assertions.assertFalse(optUtilisateur.isPresent());
    }
}
