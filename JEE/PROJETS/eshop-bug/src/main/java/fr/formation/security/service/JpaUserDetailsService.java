package fr.formation.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fr.formation.model.Utilisateur;
import fr.formation.repo.UtilisateurRepository;

@Service
public class JpaUserDetailsService implements UserDetailsService {
    @Autowired
    private UtilisateurRepository repoUtilisateur;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Utilisateur utilisateur = this.repoUtilisateur
            .findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé"));

        return User.withUsername(username)
            .password(utilisateur.getPassword())
            .roles(utilisateur.isAdmin() ? "ADMIN" : "USER")
            .build();
    }
}
