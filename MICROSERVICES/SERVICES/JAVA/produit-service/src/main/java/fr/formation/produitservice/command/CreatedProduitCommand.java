package fr.formation.produitservice.command;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Builder
public class CreatedProduitCommand {
    private String id;
    private String nom;
    private BigDecimal prix;
}
