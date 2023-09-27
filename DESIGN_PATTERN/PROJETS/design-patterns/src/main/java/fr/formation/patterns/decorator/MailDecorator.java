package fr.formation.patterns.decorator;

import fr.formation.service.IProduitService;

public class MailDecorator extends AbstractServiceDecorator {
    public MailDecorator(IProduitService srvProduit) {
        super(srvProduit);
    }

    @Override
    public void findAll() {
        this.srvProduit.findAll();
        System.out.println("MAIL ENVOYE !");
    }
}
