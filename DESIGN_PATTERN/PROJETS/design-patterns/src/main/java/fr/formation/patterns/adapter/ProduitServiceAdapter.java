package fr.formation.patterns.adapter;

import fr.formation.service.ProduitService;

public class ProduitServiceAdapter extends ProduitService implements IProduitServiceCible {
    // On adapte : getAll au lieu de findAll
    @Override
    public String getAll() {
        this.findAll(); // On appelle la méthode originale
        
        // On transforme si nécessaire ...
        return "json";
    }
}
