package fr.formation.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class Fournisseur {
    private int id;
    private String nom;
    private List<Produit> produits;
}
