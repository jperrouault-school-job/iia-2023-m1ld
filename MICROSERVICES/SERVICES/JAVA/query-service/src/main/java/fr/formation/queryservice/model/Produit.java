package fr.formation.queryservice.model;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Produit {
    @Id
    private String id;

    private String nom;
    private BigDecimal prix;
    private int note;
}
