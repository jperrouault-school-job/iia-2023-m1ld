package fr.formation.patterns.adapter;

public class Application {
    public static void main(String[] args) {
        IProduitServiceCible client = new ProduitServiceAdapter();

        System.out.println(client.getAll());
    }
}
