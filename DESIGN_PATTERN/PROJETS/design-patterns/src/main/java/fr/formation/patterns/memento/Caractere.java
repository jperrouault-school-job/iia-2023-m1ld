package fr.formation.patterns.memento;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class Caractere {
    private Character codeClavier;
    private AttributCaractere attribut;
}
