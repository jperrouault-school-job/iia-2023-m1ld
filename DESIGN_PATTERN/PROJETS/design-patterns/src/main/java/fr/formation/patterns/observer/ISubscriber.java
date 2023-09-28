package fr.formation.patterns.observer;

public interface ISubscriber {
    public void update(IPublisher publisher, Context context);
}
