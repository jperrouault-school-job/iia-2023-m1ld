package fr.formation.api;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.formation.model.Utilisateur;
import fr.formation.repo.UtilisateurRepository;
import fr.formation.request.AuthRequest;
import fr.formation.request.SubscriptionRequest;
import fr.formation.security.JwtUtil;

@RestController
@RequestMapping("/api/user")
public class UtilisateurApiController {
    @Autowired
    private UtilisateurRepository repoUtilisateur;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/auth")
    public String auth(@RequestBody AuthRequest request) {
        // On va demander à SPRING SECURITY d'authentifier l'utilisateur
        try {
            Authentication authentication = new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());

            authentication = this.authenticationManager.authenticate(authentication);

            SecurityContextHolder.getContext().setAuthentication(authentication);

            return JwtUtil.generate();
        }

        catch (BadCredentialsException e) {
            return "";
        }
    }

    @PostMapping("/subscribe")
    public String subscribe(@RequestBody SubscriptionRequest request) {
        Utilisateur utilisateur = new Utilisateur();

        BeanUtils.copyProperties(request, utilisateur);

        utilisateur.setPassword(this.passwordEncoder.encode(request.getPassword()));

        this.repoUtilisateur.save(utilisateur);

        return utilisateur.getId();
    }
}
