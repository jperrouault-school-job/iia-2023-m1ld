package fr.formation.service;

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

import fr.formation.model.Fournisseur;
import fr.formation.repo.FournisseurRepository;
import fr.formation.request.FournisseurRequest;
import jakarta.validation.ConstraintViolationException;

@SpringBootTest(classes = FournisseurService.class)
@ContextConfiguration(classes = ValidationAutoConfiguration.class)
class FournisseurServiceTest {
    @MockBean
    private FournisseurRepository repoFournisseur;
    
    @Autowired
    private FournisseurService srvFournisseur;
    
    @Test
    void shouldReturnFournisseurById() throws Exception {
        // given

        // when
        Mockito.when(this.repoFournisseur.findById(1)).thenReturn(Optional.of(new Fournisseur()));
        
        // then
        Assertions.assertNotNull(this.srvFournisseur.findById(1));
        Mockito.verify(this.repoFournisseur, Mockito.times(1)).findById(1);
    }
    
    @Test
    void shouldThrowExceptionOnFindById() {
        // given

        // when / then
        Assertions.assertThrows(ConstraintViolationException.class, () -> this.srvFournisseur.findById(0));
        
        // then
        Mockito.verify(this.repoFournisseur, Mockito.never()).findById(0);
    }
    
    @Test
    void shouldThrowExceptionOnDetailedFindById() {
        // given

        // when / then
        Assertions.assertThrows(ConstraintViolationException.class, () -> this.srvFournisseur.findDetailedById(0));
        
        // then
        Mockito.verify(this.repoFournisseur, Mockito.never()).findById(0);
    }
    
    @Test
    void shouldThrowExceptionOnSaveWhenNullOrEmptyNom() {
        // given
        FournisseurRequest fournisseur = this.generateFournisseur();
        
        fournisseur.setNom(null);
        
        // when / then
        Assertions.assertThrows(ConstraintViolationException.class, () -> this.srvFournisseur.save(null, fournisseur));
        
        // given
        fournisseur.setNom("");

        // when / then
        Assertions.assertThrows(ConstraintViolationException.class, () -> this.srvFournisseur.save(null, fournisseur));

        // then
        Mockito.verify(this.repoFournisseur, Mockito.never()).save(Mockito.any());
    }
    
    @Test
    void shouldSave() {
        // given
        FournisseurRequest request = this.generateFournisseur();
        
        // when / then
        Assertions.assertDoesNotThrow(() -> this.srvFournisseur.save(null, request));
        
        // then
        Mockito.verify(this.repoFournisseur, Mockito.times(1)).save(Mockito.any());
    }
    
    @Test
    void shouldReturnList() {
        // given
        List<Fournisseur> laListe = new ArrayList<>();
        
        laListe.add(new Fournisseur());
        
        Mockito.when(this.repoFournisseur.findAll()).thenReturn(laListe);

        // when / then
        Assertions.assertEquals(this.srvFournisseur.findAll(), laListe);

        // then
        Mockito.verify(this.repoFournisseur, Mockito.times(1)).findAll();
    }
    
    @Test
    void shouldReturnListIfNull() {
        // given
        Mockito.when(this.repoFournisseur.findAll()).thenReturn(null);

        // when / then
        Assertions.assertNotNull(this.srvFournisseur.findAll());

        // then
        Mockito.verify(this.repoFournisseur, Mockito.times(1)).findAll();
    }
    
    @Test
    void shouldDeleteById() throws Exception {
        // given

        // when
        this.repoFournisseur.deleteById(1);

        // then
        Mockito.verify(this.repoFournisseur, Mockito.times(1)).deleteById(1);
    }
    
    @Test
    void shouldThrowExceptionOnDeleteById() {
        // given

        // when / then
        Assertions.assertThrows(ConstraintViolationException.class, () -> this.srvFournisseur.deleteById(0));
        
        // then
        Mockito.verify(this.repoFournisseur, Mockito.never()).deleteById(0);
    }
    
    private FournisseurRequest generateFournisseur() {
        FournisseurRequest request = new FournisseurRequest();
        
        request.setNom("un nom");
        
        return request;
    }
}
