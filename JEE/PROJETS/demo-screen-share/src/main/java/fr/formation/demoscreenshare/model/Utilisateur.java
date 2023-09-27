package fr.formation.demoscreenshare.model;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Utilisateur {
    @Id
    @UuidGenerator
    private String id;

    private String username;
    private String password;
}
