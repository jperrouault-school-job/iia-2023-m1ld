package fr.formation.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.formation.model.Produit;

public interface ProduitRepository extends JpaRepository<Produit, Integer> {
    @Query("select p from Produit p where p.id = ?1")
    public <T> Optional<T> findByIdProtectedAs(int id, Class<T> clz);
}
