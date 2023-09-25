package fr.formation.api;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import fr.formation.model.Fournisseur;
import fr.formation.repo.FournisseurRepository;
import fr.formation.request.FournisseurRequest;
import fr.formation.response.FournisseurResponse;

@Controller
@RequestMapping("/api/fournisseur")
public class FournisseurApiController {
    private FournisseurRepository repoFournisseur;

    @GetMapping
    public List<FournisseurResponse> findAll() {
        return this.repoFournisseur.findAll()
            .stream()
            .map(f -> FournisseurResponse.builder()
                        .id(f.getId())
                        .nom(f.getNom())
                        .produitsCount(f.getProduits().size())
                .build()
            )
            .toList();
    }

    @PostMapping
    @PreAuthorize("hasRole('AMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public FournisseurResponse add(@RequestBody FournisseurRequest request) {
        Fournisseur fournisseur = new Fournisseur();

        fournisseur.setNom(request.getNom());

        this.repoFournisseur.save(fournisseur);

        return FournisseurResponse.builder()
            .id(fournisseur.getId())
            .nom(fournisseur.getNom())
            .build();
    }
}
