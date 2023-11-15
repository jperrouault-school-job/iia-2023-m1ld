package fr.formation.patterns.visitor;

public class Application {
    public static void main(String[] args) {
        DocumentHtml docHtml = new DocumentHtml();
        DocumentPdf docPdf = new DocumentPdf();
        DocumentFauteFilter filterFautes = new DocumentFauteFilter();
        DocumentLienFilter filterLiens = new DocumentLienFilter();

        docHtml.filter(filterFautes);
        docHtml.filter(filterLiens);

        docPdf.filter(filterFautes);
        docPdf.filter(filterLiens);
    }
}
