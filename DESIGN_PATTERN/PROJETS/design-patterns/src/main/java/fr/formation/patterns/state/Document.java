package fr.formation.patterns.state;

import lombok.Setter;

@Setter
public class Document {
    private IDocumentEtat etat = new DocumentEtatFerme(this);

    public void ouvrir() {
        this.etat.ouvrir();
    }

    public void enregistrer() {
        this.etat.enregistrer();
    }

    public void fermer() {
        this.etat.fermer();
    }
}
