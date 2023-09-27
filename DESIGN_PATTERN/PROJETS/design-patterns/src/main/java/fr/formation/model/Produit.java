package fr.formation.model;

import java.math.BigDecimal;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class Produit {
    private int id;
    private String nom;
    private BigDecimal prix;

    @Setter(AccessLevel.NONE)
    private String reference;

    private Fournisseur fournisseur;
    public static class Builder {
        private Produit produit;

        public static Builder builder() {
            return new Builder();
        }

        public Builder() {
            this.produit = new Produit();
        }

        public Builder reset() {
            this.produit = new Produit();
            return this;
        }

        public Produit build() {
            return this.produit;
        }

        public Builder withId(int id) {
            if (id > 0) {
                this.produit.setId(id);
            }

            return this;
        }

        public Builder withNom(String nom) {
            if (nom != null && !nom.isBlank()) {
                this.produit.setNom(nom);
            }

            return this;
        }

        public Builder withPrix(String prix) {
            return this.withPrix(new BigDecimal(prix));
        }

        public Builder withPrix(BigDecimal prix) {
            this.produit.setPrix(prix);
            return this;
        }

        public Builder withReference(String reference) {
            this.produit.reference = reference; // Intérêt d'un Builder interne
            return this;
        }

        public Builder withFournisseur(Fournisseur fournisseur) {
            this.produit.setFournisseur(fournisseur);
            return this;
        }
    }
}
