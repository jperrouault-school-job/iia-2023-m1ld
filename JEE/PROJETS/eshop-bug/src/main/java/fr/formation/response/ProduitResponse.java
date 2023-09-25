package fr.formation.response;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Builder 
public class ProduitResponse {
    private int id;
    private String nom;
    private BigDecimal prix;
    private int fournisseurId;
    private String fournisseurNom;
}
