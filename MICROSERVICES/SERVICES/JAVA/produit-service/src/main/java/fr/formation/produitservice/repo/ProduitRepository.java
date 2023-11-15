package fr.formation.produitservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.produitservice.model.Produit;

public interface ProduitRepository extends JpaRepository<Produit, String> {
    
}
