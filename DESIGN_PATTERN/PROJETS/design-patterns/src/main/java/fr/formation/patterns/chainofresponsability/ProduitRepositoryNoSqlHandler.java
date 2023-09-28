package fr.formation.patterns.chainofresponsability;

import fr.formation.model.Produit;
import fr.formation.patterns.builder.ProduitBuilder;

public class ProduitRepositoryNoSqlHandler extends AbstractHandler {
    @Override
	public Produit handle(int id) {
		if (id == 2) {
			return new ProduitBuilder()
                .withId(id)
				.withNom("NoSQL")
				.build();
		}

		return super.handle(id);
	}
}
