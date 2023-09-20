package fr.formation.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonView;

import fr.formation.api.Views;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
    @JsonView(Views.Commons.class)
    private int id;

    @JsonView(Views.Produit.class)
    private String nom;
    
    @JsonView(Views.Produit.class)
    private BigDecimal prix;

    @ManyToOne
    @JoinColumn(name = "prod_fournisseur_id")
    @JsonView(Views.Produit.class)
    private Fournisseur fournisseur;
}
