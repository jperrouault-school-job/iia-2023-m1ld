package fr.formation.patterns.state;

public class Application {
    public static void main(String[] args) {
        Document document = new Document();

        document.fermer();
        document.ouvrir();
        document.ouvrir();
        document.fermer();
        document.enregistrer();
        document.ouvrir();
        document.enregistrer();
        document.fermer();
    }
}
