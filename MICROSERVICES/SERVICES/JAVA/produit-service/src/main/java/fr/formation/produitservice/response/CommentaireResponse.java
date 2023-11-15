package fr.formation.produitservice.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Builder
public class CommentaireResponse {
    private String texte;
    private int note;
}
