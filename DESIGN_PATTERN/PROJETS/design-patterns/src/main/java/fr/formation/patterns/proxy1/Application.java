package fr.formation.patterns.proxy1;

import fr.formation.patterns.decorator.LogDecorator;
import fr.formation.patterns.decorator.MailDecorator;
import fr.formation.patterns.factory.ServiceFactory;
import fr.formation.service.IProduitService;

public class Application {
    public static final boolean USER_CONNECTED = false;

    public static void main(String[] args) {
        // Utilisation directe
        IProduitService srvProduit = new ProduitServiceProxy();

        srvProduit.findAll();


        // Utilisateur avec décorateur
        IProduitService stackedServiceProduit = ServiceFactory.createProduitService(2);

        stackedServiceProduit = new LogDecorator(stackedServiceProduit);
        stackedServiceProduit = new MailDecorator(stackedServiceProduit);

        stackedServiceProduit = new ProduitServiceProxy(stackedServiceProduit);

        stackedServiceProduit.findAll();
    }
}
