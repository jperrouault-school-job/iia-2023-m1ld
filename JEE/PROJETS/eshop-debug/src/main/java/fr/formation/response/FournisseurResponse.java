package fr.formation.response;

import lombok.Builder;
import lombok.Getter;

@Getter @Builder
public class FournisseurResponse {
    private int id;
    private String nom;
    private int produitsCount;
}
