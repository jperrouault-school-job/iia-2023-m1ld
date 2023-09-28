package fr.formation.patterns.chainofresponsability;

public class Application {
    public static void main(String[] args) {
        // Configuration des handlers
        IHandler firstHandler = new ProduitRepositorySqlHandler();

        firstHandler
            .chain(new ProduitRepositoryNoSqlHandler())
            // .chain(autre handler ...)
            // .chain(autre handler ...)
            // .chain(autre handler ...)
            ;
        
        System.out.println(firstHandler.handle(2));
        System.out.println(firstHandler.handle(1));
        System.out.println(firstHandler.handle(0));
        System.out.println(firstHandler.handle(10));
    }
}
