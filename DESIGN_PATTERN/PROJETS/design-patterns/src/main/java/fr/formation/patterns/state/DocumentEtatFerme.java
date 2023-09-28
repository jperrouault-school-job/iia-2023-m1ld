package fr.formation.patterns.state;

public class DocumentEtatFerme implements IDocumentEtat {
    private Document document;

    public DocumentEtatFerme(Document document) {
        this.document = document;
    }

    @Override
    public void ouvrir() {
        System.out.println("Ouverture du document ...");
        this.document.setEtat(new DocumentEtatOuvert(this.document));
    }

    @Override
    public void enregistrer() {
        System.out.println("Impossible d'enregistrer, ce document est fermé !");
    }

    @Override
    public void fermer() {
        System.out.println("Document déjà fermé !");
    }
}
