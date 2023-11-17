package fr.formation.queryservice.response;

import java.math.BigDecimal;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProduitByIdResponse {
    private String id;
    private String nom;
    private BigDecimal prix;
    private int note;
    private List<CommentaireResponse> commentaires;
}
