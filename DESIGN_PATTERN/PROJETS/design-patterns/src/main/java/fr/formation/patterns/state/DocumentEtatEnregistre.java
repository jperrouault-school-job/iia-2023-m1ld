package fr.formation.patterns.state;

public class DocumentEtatEnregistre implements IDocumentEtat {
    private Document document;

    public DocumentEtatEnregistre(Document document) {
        this.document = document;
    }

    @Override
    public void ouvrir() {
        System.out.println("Document en cours d'enregistrement !");
    }

    @Override
    public void enregistrer() {
        System.out.println("Document déjà en cours d'enregistrement !");
    }

    @Override
    public void fermer() {
        System.out.println("Document en cours d'enregistrement !");
    }
}
