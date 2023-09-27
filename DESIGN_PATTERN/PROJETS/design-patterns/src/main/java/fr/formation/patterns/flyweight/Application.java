package fr.formation.patterns.flyweight;

public class Application {
    public static void main(String[] args) {
        Texte texte = new Texte();
        boolean useFlyweight = true;
        
        texte.ajouter('H', 0, 12, 0, useFlyweight);
        texte.ajouter('e', 0, 12, 0, useFlyweight);
        texte.ajouter('l', 0, 12, 0, useFlyweight);
        texte.ajouter('l', 0, 12, 0, useFlyweight);
        texte.ajouter('o', 0, 12, 0, useFlyweight);
        texte.ajouter(' ', 0, 12, 0, useFlyweight);
        texte.ajouter('W', 0, 24, 0, useFlyweight);
        texte.ajouter('o', 0, 24, 0, useFlyweight);
        texte.ajouter('r', 0, 24, 0, useFlyweight);
        texte.ajouter('l', 0, 24, 0, useFlyweight);
        texte.ajouter('d', 0, 24, 0, useFlyweight);

        texte.imprimer();

        for (int i = 0; i < 1000000; i++) {
            texte.ajouter('d', 0, 24, 0, useFlyweight);
        }

        System.out.print("Mémoire utilisée : ");
        System.out.println(Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory());
    }
}
