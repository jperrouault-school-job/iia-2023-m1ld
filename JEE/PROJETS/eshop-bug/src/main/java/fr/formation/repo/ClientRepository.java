package fr.formation.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.model.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {
    
}
