package fr.formation.patterns.memento;

import java.util.ArrayList;
import java.util.List;

public class AttributFlyweight {
    private List<AttributCaractere> sharedAttributes = new ArrayList<>();

    public AttributCaractere getAttributCaractere(int couleur, int taille, int police) {
        AttributCaractere sharedAttribute = null;

        for (AttributCaractere sa : this.sharedAttributes) {
            if (sa.getCouleur() == couleur && sa.getTaille() == taille && sa.getPolice() == police) {
                sharedAttribute = sa;
                break;
            }
        }

        if (sharedAttribute == null) {
            sharedAttribute = new AttributCaractere(couleur, taille, police);
            this.sharedAttributes.add(sharedAttribute);
        }

        return sharedAttribute;
    }
    
    // Pour comparer ...
    public AttributCaractere getAttributCaractereNoFlyweight(int couleur, int taille, int police) {
        return new AttributCaractere(couleur, taille, police);
    }
}
