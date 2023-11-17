package fr.formation.produitservice.api;

import org.springframework.beans.BeanUtils;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.formation.produitservice.command.CreatedProduitCommand;
import fr.formation.produitservice.model.Produit;
import fr.formation.produitservice.repo.ProduitRepository;
import fr.formation.produitservice.request.CreateOrUpdateProduitRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/produit")
@RequiredArgsConstructor
public class ProduitApiController {
    private final ProduitRepository repository;
    private final StreamBridge streamBridge;

    @PostMapping
    public String create(@RequestBody CreateOrUpdateProduitRequest request) {
        Produit produit = new Produit();

        BeanUtils.copyProperties(request, produit);

        this.repository.save(produit);

        this.streamBridge.send("onProduitCreated-out-0", CreatedProduitCommand.builder()
            .id(produit.getId())
            .nom(produit.getNom())
            .prix(produit.getPrix())
            .build()
        );

        return produit.getId();
    }
}
