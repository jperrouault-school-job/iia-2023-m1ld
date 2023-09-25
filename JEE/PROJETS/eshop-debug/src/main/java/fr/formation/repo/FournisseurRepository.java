package fr.formation.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.formation.model.Fournisseur;

public interface FournisseurRepository extends JpaRepository<Fournisseur, Integer> {
    @Query("select f from Fournisseur f left join fetch f.produits where f.id = ?1")
    public Optional<Fournisseur> findByIdFetchingProduits(int id);
}
