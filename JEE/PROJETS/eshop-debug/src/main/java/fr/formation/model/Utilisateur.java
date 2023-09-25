package fr.formation.model;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Column;
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
    @Column(name = "usr_id")
    private String id;
    
    @Column(name = "usr_username", length = 50, nullable = false)
    private String username;

    @Column(name = "usr_password", length = 150, nullable = false)
    private String password;

    @Column(name = "usr_email", length = 150, nullable = false)
    private String email;

    @Column(name = "usr_is_admin")
    private boolean admin;
}
