package fr.formation.patterns.decorator;

import fr.formation.patterns.factory.ServiceFactory;
import fr.formation.service.IProduitService;

public class Application {
    public static void main(String[] args) {
        IProduitService stackedServiceProduit = ServiceFactory.createProduitService(2);

        stackedServiceProduit = new LogDecorator(stackedServiceProduit);
        stackedServiceProduit = new MailDecorator(stackedServiceProduit);

        stackedServiceProduit.findAll();
    }
}
