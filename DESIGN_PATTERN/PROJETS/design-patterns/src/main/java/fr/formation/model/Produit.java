package fr.formation.model;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Produit {
    private int id;
    private String nom;
    private BigDecimal prix;
}
