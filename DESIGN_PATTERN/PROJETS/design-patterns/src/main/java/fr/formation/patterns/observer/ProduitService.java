package fr.formation.patterns.observer;

import java.util.ArrayList;
import java.util.List;

public class ProduitService implements IPublisher {
    private List<ISubscriber> subscribers = new ArrayList<>();

    public void findAll() {
        System.out.println("FIND ALL PRODUIT SERVICE");

        this.notify(Context.builder()
            .method("findAll")
            .result(null)
            .build()
        );
    }

    @Override
    public void subscribe(ISubscriber subscriber) {
        this.subscribers.add(subscriber);
    }

    @Override
    public void unsubscribe(ISubscriber subscriber) {
        this.subscribers.remove(subscriber);
    }

    @Override
    public void notify(Context context) {
        this.subscribers.forEach(s -> s.update(this, context));
    }
}
