package fr.formation.produitservice.feignclient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import fr.formation.produitservice.response.CommentaireResponse;

@FeignClient(value = "commentaire-service", path = "/api/commentaire")
public interface CommentaireFeignClient {
    @GetMapping("/by-produit-id/{produitId}")
    public List<CommentaireResponse> findAllByProduitId(@PathVariable String produitId);

    @GetMapping("/note/by-produit-id/{produitId}")
    public int getNoteByProduitId(@PathVariable String produitId);
}
