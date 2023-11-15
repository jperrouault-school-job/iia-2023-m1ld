package fr.formation.patterns.visitor;

public class DocumentLienFilter implements IFilter {
    @Override
    public void visit(IDocument document) {
        System.out.println("[FILTRE LIEN] Document visité !");
    }
    
    @Override
    public void visit(DocumentPdf docPdf) {
        System.out.println("[FILTRE LIEN] PDF visité !");
    }
    
    @Override
    public void visit(DocumentHtml docHtml) {
        System.out.println("[FILTRE LIEN] HTML visité !");
    }
}
