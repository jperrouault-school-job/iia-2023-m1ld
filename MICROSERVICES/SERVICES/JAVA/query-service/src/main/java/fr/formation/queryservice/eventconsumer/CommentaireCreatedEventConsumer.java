package fr.formation.queryservice.eventconsumer;

import java.util.List;
import java.util.function.Consumer;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import fr.formation.queryservice.event.CommentaireCreatedEvent;
import fr.formation.queryservice.model.Commentaire;
import fr.formation.queryservice.model.Produit;
import fr.formation.queryservice.repo.CommentaireRepository;
import fr.formation.queryservice.repo.ProduitRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Component("onCommentaireCreated")
@RequiredArgsConstructor
public class CommentaireCreatedEventConsumer implements Consumer<CommentaireCreatedEvent> {
    private final CommentaireRepository repository;
    private final ProduitRepository produitRepository;

    @Override
    @Transactional
    public void accept(CommentaireCreatedEvent evt) {
        Commentaire commentaire = new Commentaire();
        Produit produit = this.produitRepository.findById(evt.getProduitId()).orElseThrow();

        BeanUtils.copyProperties(evt, commentaire);

        commentaire.setProduit(produit);

        this.repository.save(commentaire);

        List<Commentaire> commentaires = this.repository.findAllByProduitId(evt.getProduitId());

        produit.setNote((int)commentaires.stream().mapToInt(Commentaire::getNote).average().orElse(-1));

        this.produitRepository.save(produit);
    }
}
