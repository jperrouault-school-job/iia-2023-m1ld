package fr.formation.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity // Obligation
@Table(name = "prod")
@Getter @Setter
public class Produit {
    @Id // Obligatoire sur chaque entité
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto incrément
    @Column(name = "prod_id")
    private int id;

    private String nom;

    private BigDecimal prix;
}
