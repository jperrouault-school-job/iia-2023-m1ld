package fr.formation.produitservice.response;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Builder
public class ProduitResponse {
    private String id;
    private String nom;
    private BigDecimal prix;
    private int note;
}
