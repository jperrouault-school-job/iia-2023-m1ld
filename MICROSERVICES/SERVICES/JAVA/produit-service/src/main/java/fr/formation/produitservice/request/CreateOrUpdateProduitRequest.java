package fr.formation.produitservice.request;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateOrUpdateProduitRequest {
    private String nom;
    private BigDecimal prix;
    private boolean notable;
}
