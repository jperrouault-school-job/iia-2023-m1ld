package fr.formation.patterns.chainofresponsability;

import fr.formation.model.Produit;
import fr.formation.patterns.builder.ProduitBuilder;

public class ProduitRepositorySqlHandler extends AbstractHandler {
    @Override
	public Produit handle(int id) {
		if (id == 1) {
			return new ProduitBuilder()
                .withId(id)
				.withNom("SQL")
				.build();
		}

		return super.handle(id);
	}
}
