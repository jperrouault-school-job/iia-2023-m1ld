package fr.formation.queryservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Commentaire {
    @Id
    private String id;

    private String texte;
    private int note;

    @ManyToOne
    private Produit produit;
}
