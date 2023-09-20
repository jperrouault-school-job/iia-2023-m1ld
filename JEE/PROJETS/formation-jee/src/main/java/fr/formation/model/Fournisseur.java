package fr.formation.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;

import fr.formation.api.Views;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Fournisseur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.Commons.class)
    private int id;
    
    private String nom;

    @OneToMany(mappedBy = "fournisseur")
    private List<Produit> produits;
}
