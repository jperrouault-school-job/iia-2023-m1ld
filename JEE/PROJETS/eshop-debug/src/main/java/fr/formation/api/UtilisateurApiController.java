package fr.formation.api;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.formation.model.Utilisateur;
import fr.formation.repo.UtilisateurRepository;
import fr.formation.request.SubscriptionRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UtilisateurApiController {
    @Autowired
    private UtilisateurRepository repoUtilisateur;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/subscribe")
    public String subscribe(@Valid @RequestBody SubscriptionRequest request) {
        Utilisateur utilisateur = new Utilisateur();

        BeanUtils.copyProperties(request, utilisateur);

        utilisateur.setPassword(this.passwordEncoder.encode(request.getPassword()));

        this.repoUtilisateur.save(utilisateur);

        return utilisateur.getId();
    }
}
