package fr.formation.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "produit")
@Getter @Setter
public class Produit {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pro_id")
    private int id;
    
    @Column(name = "pro_nom", length = 100, nullable = false)
    private String nom;
    
    @Column(name = "pro_prix", nullable = false)
    private BigDecimal prix;

    @ManyToOne
    @JoinColumn(name = "pro_fournisseur_id", nullable = false)
    private Fournisseur fournisseur;
}
