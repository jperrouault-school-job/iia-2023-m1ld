package fr.formation.exo2;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Button {
    private Mediateur mediateur;
    private Command command;

    public Button(Mediateur mediateur) {
        this.mediateur = mediateur;
        mediateur.setButton(this);
    }

    public void onClick() {
        this.command.execute();
    }
}
