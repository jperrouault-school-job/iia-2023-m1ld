package fr.formation.queryservice.api;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.formation.queryservice.model.Produit;
import fr.formation.queryservice.repo.CommentaireRepository;
import fr.formation.queryservice.repo.ProduitRepository;
import fr.formation.queryservice.response.CommentaireResponse;
import fr.formation.queryservice.response.ProduitByIdResponse;
import fr.formation.queryservice.response.ProduitResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/produit")
@RequiredArgsConstructor
public class ProduitApiController {
    private final ProduitRepository repository;
    private final CommentaireRepository commentaireRepository;

    @GetMapping
    public List<ProduitResponse> findAll() {
        return this.repository.findAll()
            .stream()
            .map(p -> ProduitResponse.builder()
                .id(p.getId())
                .nom(p.getNom())
                .prix(p.getPrix())
                .note(p.getNote())
                .build()
            )
            .toList();
    }

    @GetMapping("/{id}")
    public ProduitByIdResponse findById(@PathVariable String id) {
        Produit produit = this.repository.findById(id).orElseThrow();
        ProduitByIdResponse resp = new ProduitByIdResponse();

        BeanUtils.copyProperties(produit, resp);

        resp.setCommentaires(this.commentaireRepository.findAllByProduitId(id)
            .stream()
            .map(c -> CommentaireResponse.builder()
                .texte(c.getTexte())
                .note(c.getNote())
                .build()
            )
            .toList()
        );

        return resp;
    }
}
