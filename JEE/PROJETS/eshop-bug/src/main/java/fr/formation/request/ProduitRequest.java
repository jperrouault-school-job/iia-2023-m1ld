package fr.formation.request;

import java.math.BigDecimal;

import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@Builder @NoArgsConstructor @AllArgsConstructor
public class ProduitRequest {
    private String nom;

    @Positive
    private BigDecimal prix;

    @Positive
    private Integer fournisseurId;
}
