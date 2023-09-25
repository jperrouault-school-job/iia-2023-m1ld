package fr.formation.repo;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import fr.formation.enumerator.EtatCommande;
import fr.formation.model.Client;
import fr.formation.model.Commande;
import fr.formation.model.CommandeDetail;
import fr.formation.model.Produit;


@DataJpaTest
class CommandeRepositoryTest {
    @Autowired
    private CommandeRepository repoCommande;

    @Autowired
    private ProduitRepository repoProduit;

    @Test
    void shouldAdd() {
        // given
        // Création de la commande
        Commande commande = new Commande();
        
        commande.setDate(LocalDateTime.now());
        commande.setEtat(EtatCommande.ATTENTE);
        commande.setDetails(new ArrayList<>());
        commande.setClient(new Client());
        
        // Commande pour le client #2
        commande.getClient().setId(2);
        
        // Commande avec 2 produits
        Produit p1 = this.repoProduit.findById(1).orElseThrow();
        CommandeDetail detail1 = new CommandeDetail();
        
        detail1.setProduit(p1);
        detail1.setQuantite(1);
        detail1.setPrix(p1.getPrix());
        detail1.setCommande(commande);
        commande.getDetails().add(detail1);

        Produit p2 = this.repoProduit.findById(2).orElseThrow();
        CommandeDetail detail2 = new CommandeDetail();
        
        detail2.setProduit(p2);
        detail2.setQuantite(1);
        detail2.setPrix(p2.getPrix());
        detail2.setCommande(commande);
        commande.getDetails().add(detail2);
        
        Assertions.assertEquals(0, commande.getId());
        Assertions.assertEquals(0, commande.getDetails().get(0).getId());
        
        // when
        this.repoCommande.save(commande);
        
        // then
        Assertions.assertNotEquals(0, commande.getId());
        Assertions.assertNotEquals(0, commande.getDetails().get(0).getId());
    }
}
