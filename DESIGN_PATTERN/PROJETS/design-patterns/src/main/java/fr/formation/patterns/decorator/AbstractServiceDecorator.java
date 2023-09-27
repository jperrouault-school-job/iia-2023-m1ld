package fr.formation.patterns.decorator;

import fr.formation.service.IProduitService;

public abstract class AbstractServiceDecorator implements IProduitService {
    protected final IProduitService srvProduit;

    protected AbstractServiceDecorator(IProduitService srvProduit) {
        this.srvProduit = srvProduit;
    }
}
