package fr.formation.produitservice.command;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Builder
public class RejectCommentaireCommand {
    private String commentaireId;
    private String produitId;
}
