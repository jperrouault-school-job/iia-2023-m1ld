package fr.formation.patterns.memento;

public class Application {
    public static void main(String[] args) {
        Texte texte = new Texte();
        Gardien gardien = new Gardien();
        
        texte.ajouter('H', 0, 12, 0);
        texte.ajouter('e', 0, 12, 0);
        gardien.sauvegarder(texte.sauvegarder());

        texte.ajouter('l', 0, 12, 0);
        texte.ajouter('l', 0, 12, 0);
        texte.ajouter('o', 0, 12, 0);
        texte.ajouter(' ', 0, 12, 0);
        texte.ajouter('W', 0, 24, 0);
        gardien.sauvegarder(texte.sauvegarder());

        texte.ajouter('o', 0, 24, 0);
        texte.ajouter('r', 0, 24, 0);
        gardien.sauvegarder(texte.sauvegarder());

        texte.ajouter('l', 0, 24, 0);
        texte.ajouter('d', 0, 24, 0);
        gardien.sauvegarder(texte.sauvegarder());

        texte.imprimer();
        
        System.out.println("------------- PRECEDENT (1) -");
        texte.restaurer(gardien.precedent());
        texte.imprimer();
        
        System.out.println("------------- PRECEDENT (2) -");
        texte.restaurer(gardien.precedent());
        texte.imprimer();
        
        System.out.println("------------- SUIVANT (1) -");
        texte.restaurer(gardien.suivant());
        texte.imprimer();

        System.out.println("------------- MODIFICATION -");

        texte.ajouter('m', 0, 24, 0);
        texte.ajouter('s', 0, 24, 0);
        texte.ajouter('!', 0, 24, 0);
        gardien.sauvegarder(texte.sauvegarder());

        texte.imprimer();
        
        System.out.println("------------- PRECEDENT (3) -");
        texte.restaurer(gardien.precedent());
        texte.imprimer();
        
        System.out.println("------------- SUIVANT (2) -");
        texte.restaurer(gardien.suivant());
        texte.imprimer();
        
        System.out.println("------------- SUIVANT (3) -");
        texte.restaurer(gardien.suivant());
        texte.imprimer();
    }
}
