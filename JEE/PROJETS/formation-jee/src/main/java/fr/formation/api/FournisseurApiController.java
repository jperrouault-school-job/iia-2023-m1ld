package fr.formation.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.formation.model.Fournisseur;
import fr.formation.repo.FournisseurRepository;

@RestController
@RequestMapping("/api/fournisseur")
public class FournisseurApiController {
    @Autowired
    private FournisseurRepository repoFournisseur;

    @GetMapping
    public List<Fournisseur> findAll() {
        return this.repoFournisseur.findAll();
    }

    @PostMapping
    public Fournisseur add(@RequestBody Fournisseur fournisseur) {
        return this.repoFournisseur.save(fournisseur);
    }
}
