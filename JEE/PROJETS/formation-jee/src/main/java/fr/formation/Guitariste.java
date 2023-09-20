package fr.formation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component @Lazy
public class Guitariste {
    @Autowired
    private Instrument guitare;

    public Guitariste() {
        System.out.println("GUITARISTE CREE !");
    }

    public void jouer() {
        System.out.println("Le guitariste joue ... " + guitare.son());
    }
}
