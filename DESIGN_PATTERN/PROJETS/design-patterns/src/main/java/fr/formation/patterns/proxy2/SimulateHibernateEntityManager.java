package fr.formation.patterns.proxy2;

import fr.formation.model.Fournisseur;

public class SimulateHibernateEntityManager {
    private SimulateHibernateEntityManager() { }
    
    public static Fournisseur find(int id) {
        Fournisseur fournisseur = new Fournisseur();
        
        System.out.println("SELECT * FROM fournisseur WHERE fou_id = " + id);

        // Simulation des informations injectées par une implémentation JPA
        fournisseur.setId(id);
        fournisseur.setNom("UBER EATS");
        fournisseur.setProduits(new ListProduitProxy(fournisseur));

        return fournisseur;
    }
}
