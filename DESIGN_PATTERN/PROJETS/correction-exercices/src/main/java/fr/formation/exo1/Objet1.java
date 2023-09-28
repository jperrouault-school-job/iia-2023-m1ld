package fr.formation.exo1;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Objet1 implements Copyable {
    private int id;
    private String nom;

    public Objet1 copy() {
        Objet1 obj = new Objet1();

        obj.setId(id);
        obj.setNom(nom);

        return obj;
    }
}
