package fr.formation.patterns.factory;

import fr.formation.service.IProduitService;

public class Application {
    public static void main(String[] args) {
        IProduitService srvProduit = ServiceFactory.createProduitService(2);

        srvProduit.findAll();
    }
}
