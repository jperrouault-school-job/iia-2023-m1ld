package fr.formation.produitservice.feignclient;

import java.util.ArrayList;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import fr.formation.produitservice.response.CommentaireResponse;

@FeignClient(value = "commentaire-service", path = "/api/commentaire", fallback = CommentaireFeignClient.Fallback.class)
public interface CommentaireFeignClient {
    @GetMapping("/by-produit-id/{produitId}")
    public List<CommentaireResponse> findAllByProduitId(@PathVariable String produitId);

    @GetMapping("/note/by-produit-id/{produitId}")
    public int getNoteByProduitId(@PathVariable String produitId);

    @Component
    public static class Fallback implements CommentaireFeignClient {
        @Override
        public List<CommentaireResponse> findAllByProduitId(String produitId) {
            return new ArrayList<>();
        }

        @Override
        public int getNoteByProduitId(String produitId) {
            return -1;
        }
    }
}
