package fr.formation.patterns.builder;

import java.math.BigDecimal;

import fr.formation.model.Fournisseur;
import fr.formation.model.Produit;

public class ProduitBuilder {
    private Produit produit;

    public static ProduitBuilder builder() {
        return new ProduitBuilder();
    }

    public ProduitBuilder() {
        this.produit = new Produit();
    }

    public ProduitBuilder reset() {
        this.produit = new Produit();
        return this;
    }

    public Produit build() {
        return this.produit;
    }

    public ProduitBuilder withId(int id) {
        if (id > 0) {
            this.produit.setId(id);
        }

        return this;
    }

    public ProduitBuilder withNom(String nom) {
        if (nom != null && !nom.isBlank()) {
            this.produit.setNom(nom);
        }

        return this;
    }

    public ProduitBuilder withPrix(String prix) {
        return this.withPrix(new BigDecimal(prix));
    }

    public ProduitBuilder withPrix(BigDecimal prix) {
        this.produit.setPrix(prix);
        return this;
    }

    public ProduitBuilder withReference(String reference) {
        // this.produit.setReference(reference); // Pas possible ici
        return this;
    }

    public ProduitBuilder withFournisseur(Fournisseur fournisseur) {
        this.produit.setFournisseur(fournisseur);
        return this;
    }
}
