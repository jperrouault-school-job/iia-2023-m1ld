package fr.formation.exo1;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Objet2 implements Copyable {
    private int id;
    private String nom;

    public Objet2 copy() {
        Objet2 obj = new Objet2();

        obj.setId(id);
        obj.setNom(nom);

        return obj;
    }
}
