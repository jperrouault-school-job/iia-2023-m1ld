package fr.formation.patterns.decorator;

import fr.formation.service.IProduitService;

public class LogDecorator extends AbstractServiceDecorator {
    public LogDecorator(IProduitService srvProduit) {
        super(srvProduit);
    }

    @Override
    public void findAll() {
        System.out.println("LOG AVANT ...");
        this.srvProduit.findAll();
        System.out.println("LOG APRES ...");
    }
}
