package fr.formation.patterns.proxy2;

import fr.formation.model.Fournisseur;

public class Application {
    public static void main(String[] args) {
        // Simulation des informations injectées par une implémentation JPA
        Fournisseur fournisseur = SimulateHibernateEntityManager.find(1);
        
        System.out.println(fournisseur.getProduits().size());
        System.out.println("Le deuxième appel ne déclenche pas la requête SQL");
        System.out.println(fournisseur.getProduits().size());
    }
}
