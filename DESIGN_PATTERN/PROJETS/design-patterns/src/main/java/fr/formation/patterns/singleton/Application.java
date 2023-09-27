package fr.formation.patterns.singleton;

public class Application {
    public static void main(String[] args) {
        Singleton singleton1 = Singleton.getInstance();
        Singleton singleton2 = Singleton.getInstance();
        // Singleton singleton3 = new Singleton(); // Ne fonctionne pas

        if (singleton1 == singleton2) {
            System.out.println("C'est la même instance !");
        }
    }
}
