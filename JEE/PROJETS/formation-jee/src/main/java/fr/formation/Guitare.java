package fr.formation;

import org.springframework.stereotype.Component;

@Component
public class Guitare implements Instrument {
    public String son() {
        return "GLINK GLINK GLINK";
    }
}
