package fr.formation.security.jwt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import fr.formation.model.Utilisateur;
import fr.formation.repo.UtilisateurRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
@Profile("!test")
public class JwtHeaderFilter extends OncePerRequestFilter {
    @Autowired
    private UtilisateurRepository repoUtilisateur;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("Authorization");

        if (token != null) {
            token = token.substring(7);
            Optional<String> optUsername = JwtUtil.getUsername(token);

            if (optUsername.isPresent()) {
                Utilisateur utilisateur = this.repoUtilisateur.findByUsername(optUsername.get()).orElseThrow();
                List<SimpleGrantedAuthority> authorities = new ArrayList<>();
                
                authorities.add(new SimpleGrantedAuthority(utilisateur.isAdmin() ? "ROLE_ADMIN" : "ROLE_USER"));
                
                Authentication authentication = new UsernamePasswordAuthenticationToken(optUsername.get(), null, authorities);

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        
        // On passe au filtre suivant ...
        filterChain.doFilter(request, response);
    }
}
