package fr.formation.demoscreenshare.service;

import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fr.formation.demoscreenshare.model.Utilisateur;
import fr.formation.demoscreenshare.repo.UtilisateurRepository;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class JpaUserDetailsService implements UserDetailsService, ReactiveUserDetailsService {
    private final UtilisateurRepository repository;

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        return Mono.just(this.loadUserByUsername(username));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Utilisateur utilisateur = this.repository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé"));

        return User.withUsername(username)
            .password(utilisateur.getPassword())
            .roles("USER")
            .build();
    }
}
