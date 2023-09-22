package fr.formation.repo;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import fr.formation.projection.ProduitProjection;

// @SpringBootTest // Pour tester avec un "vrai" serveur de données
@DataJpaTest
class ProduitRepositoryTest {
    @Autowired
    private ProduitRepository repo;

    @Test
    // @Sql(statements = "INSERT INTO prod (prod_id, nom, prix) VALUES (1, 'test', 1234)")
    // @Sql("classpath:/produit-ajout.sql")
    // @Sql(executionPhase = )
    void shouldProjectById() {
        Optional<ProduitProjection> optResult = this.repo.findByIdProtectedAs(1, ProduitProjection.class);

        Assertions.assertTrue(optResult.isPresent());
        Assertions.assertEquals("test", optResult.get().getNom());
    }
}
