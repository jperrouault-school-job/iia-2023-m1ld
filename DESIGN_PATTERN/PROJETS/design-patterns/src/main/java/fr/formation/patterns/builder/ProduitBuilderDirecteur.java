package fr.formation.patterns.builder;

import fr.formation.model.Fournisseur;

public class ProduitBuilderDirecteur {
    private ProduitBuilderDirecteur() { }
    
    public static ProduitBuilder createFoodProduct(ProduitBuilder builder) {
        Fournisseur fournisseur = new Fournisseur();

        builder.reset();
        fournisseur.setNom("MIAM");

        return builder
            .withNom("Food")
            .withPrix("200")
            .withFournisseur(fournisseur);
    }
}
