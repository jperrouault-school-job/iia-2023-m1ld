package fr.formation.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import fr.formation.exception.EntityNotDeletedException;
import fr.formation.exception.EntityNotFoundException;
import fr.formation.exception.EntityNotPersistedException;
import fr.formation.model.Fournisseur;
import fr.formation.repo.FournisseurRepository;
import fr.formation.request.FournisseurRequest;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

@Service
@Validated
public class FournisseurService {
    @Autowired
    private FournisseurRepository repoFournisseur;

    public List<Fournisseur> findAll() {
        return Optional
                .ofNullable(this.repoFournisseur.findAll())
                .orElse(new ArrayList<>());
    }

    public Fournisseur findById(@Valid @Positive int id) {
        return this.repoFournisseur.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public Fournisseur findDetailedById(@Valid @Positive int id) {
        return this.repoFournisseur.findByIdFetchingProduits(id).orElseThrow(EntityNotFoundException::new);
    }

    public Fournisseur save(@Nullable Integer id, @Valid FournisseurRequest fournisseurRequest) {
        Fournisseur fournisseur = new Fournisseur();

        if (id != null) {
            fournisseur.setId(id);
        }

        fournisseur.setNom(fournisseurRequest.getNom());

        return this.save(fournisseur);
    }

    public void deleteById(@Valid @Positive int id) {
        try {
            this.repoFournisseur.deleteById(id);
        }

        catch (Exception e) {
            throw new EntityNotDeletedException();
        }
    }

    private Fournisseur save(@Valid Fournisseur fournisseur) {
        try {
            return this.repoFournisseur.save(fournisseur);
        }

        catch (Exception e) {
            throw new EntityNotPersistedException();
        }
    }
}
