package fr.formation.patterns.proxy1;

import fr.formation.patterns.factory.ServiceFactory;
import fr.formation.service.IProduitService;

public class ProduitServiceProxy implements IProduitService {
    private IProduitService srvProduit; // La référence est optionelle, contrairement au Decorator

    public ProduitServiceProxy() { } // La référence est optionelle, contrairement au Decorator

    public ProduitServiceProxy(IProduitService srvProduit) {
        this.srvProduit = srvProduit;
    }
    
    private void load() {
        if (this.srvProduit == null) {
            this.srvProduit = ServiceFactory.createProduitService(2);
        }
    }

    @Override
    public void findAll() {
        // Contrairement au Decorator, on ajoute pas un comportement, on contrôle l'accès (on déclenche, ou on déclenche pas)
        if (Application.USER_CONNECTED) { // Pas terrible, mais pour l'exemple
            this.load();
            this.srvProduit.findAll();
        }

        // Lever une exception par exemple, ou choisir de ne rien faire
        else {
            System.out.println("L'utilisateur n'est pas connecté.");
        }
    }
}
