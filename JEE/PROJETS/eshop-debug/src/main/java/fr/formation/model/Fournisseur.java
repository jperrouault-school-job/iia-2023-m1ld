package fr.formation.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "fournisseur")
@PrimaryKeyJoinColumn(name = "fou_id")
@Getter @Setter
public class Fournisseur extends Personne {
    @OneToMany(mappedBy = "fournisseur")
    private List<Produit> produits;
}
