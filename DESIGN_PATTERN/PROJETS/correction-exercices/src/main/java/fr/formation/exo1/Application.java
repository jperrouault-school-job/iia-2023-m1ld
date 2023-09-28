package fr.formation.exo1;

import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        List<Copyable> objets = new ArrayList<>();
        List<Copyable> copies = new ArrayList<>();

        copies.addAll(objets.stream().map(Copyable::copy).toList());
    }
}
