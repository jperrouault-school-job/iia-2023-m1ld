package fr.formation.response;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Builder
@Setter @NoArgsConstructor @AllArgsConstructor
public class ProduitResponse {
    private int id;
    private String nom;
    private BigDecimal prix;
    private int fournisseurId;
    private String fournisseurNom;
}
