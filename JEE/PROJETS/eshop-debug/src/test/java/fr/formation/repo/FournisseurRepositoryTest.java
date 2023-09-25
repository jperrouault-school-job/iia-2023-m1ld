package fr.formation.repo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;

import fr.formation.model.Fournisseur;
import jakarta.transaction.Transactional;

@DataJpaTest
@ImportAutoConfiguration(PersistenceExceptionTranslationPostProcessor.class)
class FournisseurRepositoryTest {
    @Autowired
    private FournisseurRepository repoFournisseur;
    
    @Test
    void testFindAll() {
        // given

        // when
        List<Fournisseur> fournisseurs = this.repoFournisseur.findAll();

        // then
        Assertions.assertNotNull(fournisseurs);
        Assertions.assertTrue(fournisseurs.size() > 0);
        Assertions.assertEquals(1, fournisseurs.get(0).getId());
    }
    
    @Test
    @Transactional
    void testFindById() {
        // given

        // when
        Optional<Fournisseur> fournisseur = this.repoFournisseur.findById(1);

        // then
        Assertions.assertNotNull(fournisseur);
        Assertions.assertTrue(fournisseur.isPresent());
        Assertions.assertEquals(1, fournisseur.get().getId());
        Assertions.assertNotEquals(0, fournisseur.get().getProduits().size());
    }
    
    @Test
    void testFindByIdFetchingProduits() {
        //given

        // when
        Optional<Fournisseur> fournisseur = this.repoFournisseur.findByIdFetchingProduits(3);

        // then
        Assertions.assertNotNull(fournisseur);
        Assertions.assertTrue(fournisseur.isPresent());
        Assertions.assertEquals(3, fournisseur.get().getId());
        Assertions.assertNotEquals(0, fournisseur.get().getProduits().size());
    }
    
    @Test
    void shouldAdd() {
        //given
        Fournisseur fournisseur = new Fournisseur();
        String randomName = UUID.randomUUID().toString();

        fournisseur.setNom(randomName);
        
        Assertions.assertEquals(0, fournisseur.getId());

        // when
        this.repoFournisseur.save(fournisseur);

        // then
        Assertions.assertNotEquals(0, fournisseur.getId());
    }
    
    @Test
    void shouldUpdate() {
        // given
        Fournisseur fournisseur = this.repoFournisseur.findById(1).get();
        String randomName = UUID.randomUUID().toString();
        
        fournisseur.setNom(randomName);

        // when
        this.repoFournisseur.save(fournisseur);
        fournisseur = this.repoFournisseur.findById(1).get();
        
        // then
        Assertions.assertEquals(randomName, fournisseur.getNom());
    }
    
    @Test
    void shouldDelete() {
        //given

        // when
        this.repoFournisseur.deleteById(4);
        Optional<Fournisseur> fournisseur = this.repoFournisseur.findById(4);

        // then
        Assertions.assertNotNull(fournisseur);
        Assertions.assertTrue(fournisseur.isEmpty());
    }
}
