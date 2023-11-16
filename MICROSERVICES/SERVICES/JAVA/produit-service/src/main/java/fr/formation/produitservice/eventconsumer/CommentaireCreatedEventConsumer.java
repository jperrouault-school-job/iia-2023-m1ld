package fr.formation.produitservice.eventconsumer;

import java.util.Optional;
import java.util.function.Consumer;

import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;

import fr.formation.produitservice.command.RejectCommentaireCommand;
import fr.formation.produitservice.command.ValidateCommentaireCommand;
import fr.formation.produitservice.event.CommentaireCreatedEvent;
import fr.formation.produitservice.model.Produit;
import fr.formation.produitservice.repo.ProduitRepository;
import lombok.RequiredArgsConstructor;

@Component("onCommentaireCreated")
@RequiredArgsConstructor
public class CommentaireCreatedEventConsumer implements Consumer<CommentaireCreatedEvent> {
    private final ProduitRepository repository;
    private final StreamBridge streamBridge;

    @Override
    public void accept(CommentaireCreatedEvent evt) {
        Optional<Produit> optProduit = this.repository.findById(evt.getProduitId());

        if (optProduit.isPresent() && optProduit.get().isNotable()) {
            this.streamBridge.send("onCommentaireCreated-out-0", ValidateCommentaireCommand.builder()
                .commentaireId(evt.getCommentaireId())
                .produitId(evt.getProduitId())
                .build()
            );
        }

        else {
            this.streamBridge.send("onCommentaireCreated-out-1", RejectCommentaireCommand.builder()
                .commentaireId(evt.getCommentaireId())
                .produitId(evt.getProduitId())
                .build()
            );
        }
    }
}
