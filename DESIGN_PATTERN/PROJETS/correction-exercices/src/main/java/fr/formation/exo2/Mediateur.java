package fr.formation.exo2;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Mediateur {
    private Button button;
    private Page page;
    private Request request;

    public void httpRequest() {
        this.request.exec();
        this.page.reload();
    }
}
