package fr.formation.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.model.Commande;

public interface CommandeRepository extends JpaRepository<Commande, Integer> {
    
}
