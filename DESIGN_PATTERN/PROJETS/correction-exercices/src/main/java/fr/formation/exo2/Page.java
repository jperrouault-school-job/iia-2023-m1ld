package fr.formation.exo2;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Page {
    private Mediateur mediateur;

    public Page(Mediateur mediateur) {
        this.mediateur = mediateur;
        mediateur.setPage(this);
    }
    
    public void reload() {
        System.out.println("Mise à jour de la page ...");
    }
}
