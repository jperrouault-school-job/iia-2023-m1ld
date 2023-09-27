package fr.formation.patterns.builder;

import fr.formation.model.Produit;

public class Application {
    public static void main(String[] args) {
        // Produit produit = new ProduitBuilder()
        Produit produit = ProduitBuilder.builder()
            .withId(-3)
            .withNom("Le nom")
            .withPrix("10")
            .withReference("REF 1103")
            .build();
        
        System.out.println(produit.toString());

        System.out.println("-- Exemple avec Directeur --");

        Produit produit2 = ProduitBuilderDirecteur
            .createFoodProduct(new ProduitBuilder())
            .withNom("Sushi")
            .build();
        
        System.out.println(produit2.toString());


        System.out.println("-- Exemple avec Builder interne --");

        Produit produit3 = Produit.Builder.builder()
            .withId(-3)
            .withNom("Le nom")
            .withPrix("10")
            .withReference("REF 1103")
            .build();

        System.out.println(produit3);
    }
}
