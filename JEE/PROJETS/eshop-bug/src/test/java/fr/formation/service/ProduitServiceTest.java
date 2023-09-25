package fr.formation.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import fr.formation.model.Produit;
import fr.formation.repo.ProduitRepository;
import fr.formation.request.ProduitRequest;
import jakarta.validation.ConstraintViolationException;

@SpringBootTest(classes = ProduitService.class)
@ContextConfiguration(classes = ValidationAutoConfiguration.class)
class ProduitServiceTest {
    @MockBean
    private ProduitRepository repoProduit;
    
    @Autowired
    private ProduitService srvProduit;
    
    @Test
    void shouldReturnProduitById() throws Exception {
        // given
        Mockito.when(this.repoProduit.findById(1)).thenReturn(Optional.of(new Produit()));
        
        // when / then
        Assertions.assertNotNull(this.srvProduit.findById(1));
        
        // then
        Mockito.verify(this.repoProduit, Mockito.times(1)).findById(1);
    }
    
    @Test
    void shouldThrowExceptionOnFindById() {
        // given

        // when / then
        Assertions.assertThrows(ConstraintViolationException.class, () -> this.srvProduit.findById(0));
        
        // then
        Mockito.verify(this.repoProduit, Mockito.never()).findById(0);
    }
    
    @Test
    void shouldReturnProduitByNom() throws Exception {
        // given
        Mockito.when(this.repoProduit.findByNom(Mockito.anyString())).thenReturn(Optional.of(new Produit()));
        
        // when / then
        Assertions.assertNotNull(this.srvProduit.findByNom("test"));
        
        // then
        Mockito.verify(this.repoProduit, Mockito.times(1)).findByNom(Mockito.anyString());
    }
    
    @Test
    void shouldThrowExceptionOnFindByNom() {
        // given

        // when / then
        Assertions.assertThrows(ConstraintViolationException.class, () -> this.srvProduit.findByNom(null));
        Assertions.assertThrows(ConstraintViolationException.class, () -> this.srvProduit.findByNom(""));
        
        // then
        Mockito.verify(this.repoProduit, Mockito.never()).findById(0);
    }
    
    @Test
    void shouldThrowExceptionOnSaveWhenNullOrEmptyNom() {
        // given
        ProduitRequest request = this.generateProduit();
        
        request.setNom(null);

        // when / then
        Assertions.assertThrows(ConstraintViolationException.class, () -> this.srvProduit.save(null, request));
        
        // given
        request.setNom("");

        // when / then
        Assertions.assertThrows(ConstraintViolationException.class, () -> this.srvProduit.save(null, request));

        // then
        Mockito.verify(this.repoProduit, Mockito.never()).save(Mockito.any());
    }
    
    @Test
    void shouldThrowExceptionOnSaveWhenWrongPrix() {
        // give
        ProduitRequest request = this.generateProduit();
        
        request.setPrix(new BigDecimal("-1"));

        // when / then
        Assertions.assertThrows(ConstraintViolationException.class, () -> this.srvProduit.save(null, request));
        
        // then
        Mockito.verify(this.repoProduit, Mockito.never()).save(Mockito.any());
    }
    
    @Test
    void shouldThrowExceptionOnSaveWhenNullOrEmptyFournisseur() {
        // given
        ProduitRequest request = this.generateProduit();
        
        request.setFournisseurId(0);

        // when / then
        Assertions.assertThrows(ConstraintViolationException.class, () -> this.srvProduit.save(null, request));
        
        // then
        Mockito.verify(this.repoProduit, Mockito.never()).save(Mockito.any());
    }
    
    @Test
    void shouldSave() {
        // given
        ProduitRequest request = this.generateProduit();
        
        // when / then
        Assertions.assertDoesNotThrow(() -> this.srvProduit.save(null, request));

        // then
        Mockito.verify(this.repoProduit, Mockito.times(1)).save(Mockito.any());
    }
    
    @Test
    void shouldReturnList() {
        // given
        List<Produit> laListe = new ArrayList<>();
        
        laListe.add(new Produit());
        
        Mockito.when(this.repoProduit.findAll()).thenReturn(laListe);
        
        // when / then
        Assertions.assertEquals(this.srvProduit.findAll(), laListe);

        // then
        Mockito.verify(this.repoProduit, Mockito.times(1)).findAll();
    }
    
    @Test
    void shouldReturnListIfNull() {
        // given
        Mockito.when(this.repoProduit.findAll()).thenReturn(null);

        // when / then
        Assertions.assertNotNull(this.srvProduit.findAll());

        // then
        Mockito.verify(this.repoProduit, Mockito.times(1)).findAll();
    }
    
    @Test
    void shouldDeleteById() throws Exception {
        // given

        // when
        this.repoProduit.deleteById(1);

        // then
        Mockito.verify(this.repoProduit, Mockito.times(1)).deleteById(1);
    }
    
    @Test
    void shouldThrowExceptionOnDeleteById() {
        // give

        // when / then
        Assertions.assertThrows(ConstraintViolationException.class, () -> this.srvProduit.deleteById(0));
        
        // then
        Mockito.verify(this.repoProduit, Mockito.never()).deleteById(0);
    }
    
    private ProduitRequest generateProduit() {
        ProduitRequest request = new ProduitRequest();
        
        request.setNom("un nom");
        request.setPrix(new BigDecimal("1"));
        request.setFournisseurId(1);
        
        return request;
    }
}
