package fr.formation.model;

import java.math.BigDecimal;

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

@Entity
@Table(name = "commande_detail")
@Getter @Setter
public class CommandeDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cdet_id")
    private int id;
    
    @Column(name = "cdet_prix", nullable = false)
    private BigDecimal prix;
    
    @Column(name = "cdet_quantite", nullable = false)
    private int quantite;

    @ManyToOne
    @JoinColumn(name = "cdet_commande_id", nullable = false)
    private Commande commande;

    @ManyToOne
    @JoinColumn(name = "cdet_produit_id", nullable = false)
    private Produit produit;
}
