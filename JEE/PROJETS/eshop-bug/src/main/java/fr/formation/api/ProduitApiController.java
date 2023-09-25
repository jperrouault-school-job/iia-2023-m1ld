package fr.formation.api;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fr.formation.model.Produit;
import fr.formation.request.ProduitRequest;
import fr.formation.response.ProduitResponse;
import fr.formation.service.ProduitService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class ProduitApiController {
    private final ProduitService srvProduit;
    
    @GetMapping
    public List<ProduitResponse> findAll() {
        return this.srvProduit.findAll()
            .stream()
            .map(p -> ProduitResponse.builder()
                .id(p.getId())
                .nom(p.getNom())
                .fournisseurId(p.getFournisseur().getId())
                .fournisseurNom(p.getFournisseur().getNom())
                .build())
            .toList();
    }
    
    @GetMapping("/{id}")
    public ProduitResponse findById(@PathVariable int id) {
        Produit produit = this.srvProduit.findById(id);

        return ProduitResponse.builder()
            .id(produit.getId())
            .nom(produit.getNom())
            .prix(produit.getPrix())
            .fournisseurId(produit.getFournisseur().getId())
            .fournisseurNom(produit.getFournisseur().getNom())
            .build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer add(@Valid @RequestBody ProduitRequest request) {
        return this.srvProduit.save(null, request).getId();
    }

    @PostMapping("/{id}")
    public Integer edit(@PathVariable int id, @Valid @RequestBody ProduitRequest request) {
        return this.srvProduit.save(id, request).getId();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable int id) {
        this.srvProduit.deleteById(id);
    }
}
