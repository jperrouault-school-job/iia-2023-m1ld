package fr.formation.patterns.memento;

import java.util.ArrayList;
import java.util.List;

public class Texte {
    private AttributFlyweight attributFlyweight = new AttributFlyweight();
    private List<Caractere> caracteres = new ArrayList<>();

    public void ajouter(Character codeClavier, int couleur, int taille, int police) {
        this.caracteres.add(new Caractere(codeClavier, attributFlyweight.getAttributCaractere(couleur, taille, police)));
    }

    public void imprimer() {
        for (Caractere c : this.caracteres) {
            System.out.print(c.getCodeClavier());
        }
        System.out.println("");
    }

    public MementoTexte sauvegarder() {
        MementoTexte mementoTexte = new MementoTexte();
        this.caracteres.forEach(mementoTexte.getCaracteres()::add);

        return mementoTexte;
    }

    public void restaurer(IMemento memento) {
        if (memento instanceof MementoTexte mementoTexte) {
            this.caracteres = new ArrayList<>();
            mementoTexte.getCaracteres().forEach(this.caracteres::add);
        }
    }
}
