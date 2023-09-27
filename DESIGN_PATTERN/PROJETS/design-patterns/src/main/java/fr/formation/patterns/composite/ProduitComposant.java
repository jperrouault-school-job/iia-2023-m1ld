package fr.formation.patterns.composite;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Builder
public class ProduitComposant implements Composant {
    private Composant parent;
    private BigDecimal prix;
}
