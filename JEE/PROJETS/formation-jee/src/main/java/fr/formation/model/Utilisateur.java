package fr.formation.model;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "[user]")
@Getter @Setter
public class Utilisateur {
    @Id
    @UuidGenerator
    private String id;

    private String username;
    private String password;
    private String email;
    private boolean admin;
}
