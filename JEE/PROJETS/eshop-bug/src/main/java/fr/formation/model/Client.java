package fr.formation.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "client")
@PrimaryKeyJoinColumn(name = "cli_id")
@Getter @Setter
public class Client extends Personne {
    @Column(name = "cli_prenom")
    private String prenom;

    @Column(name = "cli_date_naissance")
    private LocalDate dateNaissance;

    @OneToMany(mappedBy = "client")
    private List<Commande> commandes;
}
