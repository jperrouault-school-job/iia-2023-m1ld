package fr.formation.projection;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Value;

public interface ProduitWithFournisseurProjection {
    public int getId();
    public String getNom();
    public BigDecimal getPrix();

    @Value("#{target.fournisseur?.id}")
    public Integer getFournisseurId();

    @Value("#{target.fournisseur?.nom}")
    public String getFournisseurNom();
}
