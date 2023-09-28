package fr.formation.exo2;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Request {
    private Mediateur mediateur;

    public Request(Mediateur mediateur) {
        this.mediateur = mediateur;
        mediateur.setRequest(this);
    }
    
    public void exec() {
        System.out.println("Exécution de la requête HTTP ...");
    }
}
