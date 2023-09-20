package fr.formation.request;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProduitRequest {
    private String nom;
    private BigDecimal prix;
    private int fournisseurId;
}
