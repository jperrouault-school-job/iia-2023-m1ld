package fr.formation.queryservice.eventconsumer;

import java.util.function.Consumer;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import fr.formation.queryservice.event.ProduitCreatedEvent;
import fr.formation.queryservice.model.Produit;
import fr.formation.queryservice.repo.ProduitRepository;
import lombok.RequiredArgsConstructor;

@Component("onProduitCreated")
@RequiredArgsConstructor
public class ProduitCreatedEventConsumer implements Consumer<ProduitCreatedEvent> {
    private final ProduitRepository repository;

    @Override
    public void accept(ProduitCreatedEvent evt) {
        Produit produit = new Produit();

        BeanUtils.copyProperties(evt, produit);

        this.repository.save(produit);
    }
}
