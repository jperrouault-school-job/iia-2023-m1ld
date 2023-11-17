package fr.formation.queryservice.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.queryservice.model.Commentaire;

public interface CommentaireRepository extends JpaRepository<Commentaire, String> {
    public List<Commentaire> findAllByProduitId(String produitId);
}
