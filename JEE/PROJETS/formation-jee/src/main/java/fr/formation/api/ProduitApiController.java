package fr.formation.api;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import fr.formation.exception.ProduitNotFoundException;
import fr.formation.model.Produit;
import fr.formation.projection.ProduitProjection;
import fr.formation.projection.ProduitWithFournisseurProjection;
import fr.formation.repo.ProduitRepository;
import fr.formation.response.ProduitResponse;

@RestController
@RequestMapping("/api/produit")
public class ProduitApiController {
    @Autowired
    private ProduitRepository repoProduit;
    
    @GetMapping
    @JsonView(Views.Produit.class)
    public List<Produit> findAll() {
        return this.repoProduit.findAll();
    }
    
    @GetMapping("/{id}")
    public ProduitResponse findById(@PathVariable int id) {
        Produit produit = this.repoProduit.findById(id).orElseThrow(ProduitNotFoundException::new);
        ProduitResponse resp = new ProduitResponse();

        BeanUtils.copyProperties(produit, resp);

        return resp;

        // return ProduitResponse.builder()
        //     .id(produit.getId())
        //     .nom(produit.getNom())
        //     .prix(produit.getPrix())
        //     .fournisseurId(produit.getFournisseur().getId())
        //     .fournisseurNom(produit.getFournisseur().getNom())
        //     .build();
    }
    
    @GetMapping("/{id}/projected")
    public ProduitProjection findByIdProjected(@PathVariable int id) {
        return this .repoProduit.findByIdProtectedAs(id, ProduitProjection.class)
                    .orElseThrow(ProduitNotFoundException::new);
    }
    
    @GetMapping("/{id}/projected-with-fournisseur")
    public ProduitWithFournisseurProjection findByIdProjectedWithFournisseur(@PathVariable int id) {
        return this .repoProduit.findByIdProtectedAs(id, ProduitWithFournisseurProjection.class)
                    .orElseThrow(ProduitNotFoundException::new);
    }

    @PostMapping
    public Produit add(@RequestBody Produit produit) {
        this.repoProduit.save(produit);
        return produit;
    }
}
