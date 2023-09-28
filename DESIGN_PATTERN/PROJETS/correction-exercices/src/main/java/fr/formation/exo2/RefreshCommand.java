package fr.formation.exo2;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RefreshCommand implements Command {
    private Mediateur mediateur;

    @Override
    public void execute() {
        System.out.println("Exécution de la commande refresh par ... bouton ou autre");
        this.mediateur.httpRequest();
    }
}
