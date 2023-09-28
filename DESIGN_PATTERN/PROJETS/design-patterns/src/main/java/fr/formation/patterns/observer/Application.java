package fr.formation.patterns.observer;

public class Application {
    public static void main(String[] args) {
        ProduitService srvProduit = new ProduitService();

        srvProduit.subscribe(new MailSubscriber()); 
        srvProduit.subscribe(new LogSubscriber());

        srvProduit.findAll();
    }
}
