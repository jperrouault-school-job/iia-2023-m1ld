package fr.formation.patterns.chainofresponsability;

import fr.formation.model.Produit;

public abstract class AbstractHandler implements IHandler {
    private IHandler nextHandler;

    @Override
    public IHandler chain(IHandler handler) {
        this.nextHandler = handler;
        return handler;
    }

    public Produit handle(int id) {
		if (this.nextHandler != null) {
			return this.nextHandler.handle(id);
		}

		return null;
	}
}
