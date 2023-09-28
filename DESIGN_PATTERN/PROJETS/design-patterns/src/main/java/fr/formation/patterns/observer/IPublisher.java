package fr.formation.patterns.observer;

public interface IPublisher {
    public void subscribe(ISubscriber subscriber);
    public void unsubscribe(ISubscriber subscriber);
    public void notify(Context context);
}
