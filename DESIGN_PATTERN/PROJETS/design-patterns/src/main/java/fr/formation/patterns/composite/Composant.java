package fr.formation.patterns.composite;

import java.math.BigDecimal;

public interface Composant {
    public void setParent(Composant parent);
    public BigDecimal getPrix();
}
