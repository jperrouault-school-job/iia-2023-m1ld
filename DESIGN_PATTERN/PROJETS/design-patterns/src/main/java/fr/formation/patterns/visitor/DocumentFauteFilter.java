package fr.formation.patterns.visitor;

public class DocumentFauteFilter implements IFilter {
    @Override
    public void visit(IDocument document) {
        System.out.println("[FILTRE FAUTES] Document visité !");
    }
    
    @Override
    public void visit(DocumentPdf docPdf) {
        System.out.println("[FILTRE FAUTES] PDF visité !");
    }
    
    @Override
    public void visit(DocumentHtml docHtml) {
        System.out.println("[FILTRE FAUTES] HTML visité !");
    }
}
