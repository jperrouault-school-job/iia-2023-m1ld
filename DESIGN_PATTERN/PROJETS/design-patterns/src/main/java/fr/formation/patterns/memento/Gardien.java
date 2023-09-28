package fr.formation.patterns.memento;

import java.util.ArrayList;
import java.util.List;

public class Gardien {
    private List<IMemento> mementos = new ArrayList<>();
    private int index = -1;

    public void sauvegarder(IMemento memento) {
        if (this.index < this.mementos.size() - 1) {
            this.mementos = this.mementos.subList(0, this.index + 1);
        }
        
        this.mementos.add(memento);
        this.index++;
    }

    public IMemento precedent() {
        try {
            return this.mementos.get(--this.index);
        }

        catch (IndexOutOfBoundsException e) {
            this.index = 0;
            return this.mementos.get(this.index);
        }
    }

    public IMemento suivant() {
        try {
            return this.mementos.get(++this.index);
        }

        catch (IndexOutOfBoundsException e) {
            this.index = this.mementos.size() - 1;
            return this.mementos.get(this.index);
        }
    }
}
