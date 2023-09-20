package fr.formation.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.formation.exception.ProduitNotFoundException;
import fr.formation.model.Produit;
import fr.formation.repo.ProduitRepository;

@RestController
@RequestMapping("/api/produit")
public class ProduitApiController {
    @Autowired
    private ProduitRepository repoProduit;
    
    @GetMapping
    public List<Produit> findAll() {
        return this.repoProduit.findAll();
    }
    
    @GetMapping("/{id}")
    public Produit findById(@PathVariable int id) {
        return this.repoProduit.findById(id).orElseThrow(ProduitNotFoundException::new);
    }

    @PostMapping
    public Produit add(@RequestBody Produit produit) {
        this.repoProduit.save(produit);
        return produit;
    }
}
