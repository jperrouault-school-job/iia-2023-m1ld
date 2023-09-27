package fr.formation.patterns.factory;

import fr.formation.service.IProduitService;
import fr.formation.service.ProduitService;
import fr.formation.service.ProduitServiceV2;

public class ServiceFactory {
    private ServiceFactory() { }

    public static IProduitService createProduitService() {
        return createProduitService(1);
    }

    public static IProduitService createProduitService(int version) {
        if (version == 2) {
            return new ProduitServiceV2();
        }

        return new ProduitService();
    }
}
