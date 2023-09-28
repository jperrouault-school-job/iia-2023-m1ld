package fr.formation.patterns.observer;

public class MailSubscriber implements ISubscriber {
    @Override
    public void update(IPublisher publisher, Context context) {
        System.out.println("ENVOIE MAIL ...");
        System.out.println(publisher);
        System.out.println(context);
    }
}
