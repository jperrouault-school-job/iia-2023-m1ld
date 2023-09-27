package fr.formation.patterns.flyweight;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class AttributCaractere {
    private int couleur;
    private int taille;
    private int police;
}
