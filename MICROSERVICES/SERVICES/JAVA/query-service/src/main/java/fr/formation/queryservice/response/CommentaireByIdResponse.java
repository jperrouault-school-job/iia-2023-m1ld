package fr.formation.queryservice.response;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CommentaireByIdResponse {
    private String id;
    private String texte;
    private int note;
    private String produitId;
    private String produitNom;
}
