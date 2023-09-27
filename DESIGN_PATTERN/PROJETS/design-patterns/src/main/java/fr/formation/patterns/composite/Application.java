package fr.formation.patterns.composite;

import java.math.BigDecimal;

public class Application {
    public static void main(String[] args) {
        PackComposite pack1 = new PackComposite();
        PackComposite pack2 = new PackComposite();

        pack1.ajouter(ProduitComposant.builder().prix(BigDecimal.valueOf(10)).build());
        pack1.ajouter(ProduitComposant.builder().prix(BigDecimal.valueOf(20)).build());
        
        pack2.ajouter(pack1);
        pack2.ajouter(ProduitComposant.builder().prix(BigDecimal.valueOf(100)).build());

        System.out.println("Total du pack 1");
        System.out.println(pack1.getPrix());

        System.out.println("Total du pack 2");
        System.out.println(pack2.getPrix());
    }
}
