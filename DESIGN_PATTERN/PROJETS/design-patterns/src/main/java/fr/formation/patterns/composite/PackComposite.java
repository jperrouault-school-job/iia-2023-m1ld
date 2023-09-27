package fr.formation.patterns.composite;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PackComposite implements Composant {
    private Composant parent;
    private List<Composant> enfants = new ArrayList<>();

    public void ajouter(Composant enfant) {
        this.enfants.add(enfant);
        enfant.setParent(this);
    }

    public void retirer(Composant enfant) {
        this.enfants.remove(enfant);
        enfant.setParent(null);
    }

    @Override
    public BigDecimal getPrix() {
        return this.enfants.stream()
            .map(Composant::getPrix) // Transforme le composant en BigDecimal (prix)
            .reduce(BigDecimal.valueOf(0f), BigDecimal::add); // réduit la liste des prix à un prix total
    }
}
