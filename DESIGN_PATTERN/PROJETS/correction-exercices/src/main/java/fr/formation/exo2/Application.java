package fr.formation.exo2;

public class Application {
    public static void main(String[] args) {
        Mediateur mediateur = new Mediateur();
        Button btn = new Button(mediateur);
        RefreshCommand cmd = new RefreshCommand(mediateur);

        new Request(mediateur);
        new Page(mediateur);

        btn.setCommand(cmd);
        btn.onClick();
    }
}
