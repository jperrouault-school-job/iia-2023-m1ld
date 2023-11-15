package fr.formation.patterns.visitor;

public class DocumentHtml implements IDocument {
    @Override
    public void filter(IFilter filter) {
        filter.visit(this);
    }
}
