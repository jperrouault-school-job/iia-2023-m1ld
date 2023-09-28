package fr.formation.patterns.state.nostate;

public class Document {
    private DocumentEtat etat = DocumentEtat.FERME;

    public void ouvrir() {
        if (this.etat == DocumentEtat.FERME) {
            System.out.println("Ouverture du document ...");
            this.etat = DocumentEtat.OUVERT;
        }

        else {
            System.out.println("Document déjà ouvert ou en cours d'enregistrement !");
        }
    }

    public void enregistrer() {
        if (this.etat == DocumentEtat.OUVERT) {
            System.out.println("Enregistrement en cours ...");
            this.etat = DocumentEtat.ENREGISTRE;

            Thread simulateSaving = new Thread(() -> {
                try {
                    Thread.sleep(1000);
                }
                
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
                
                System.out.println("Enregistrement terminé !");
                this.etat = DocumentEtat.OUVERT;
            });
    
            simulateSaving.start();
        }

        else {
            System.out.println("Impossible d'enregistrer ! Document fermé ou en cours d'enregistrement.");
        }
    }

    public void fermer() {
        if (this.etat == DocumentEtat.OUVERT) {
            System.out.println("Fermeture en cours ...");
        }

        else if (this.etat == DocumentEtat.FERME) {
            System.out.println("Document déjà fermé !");
        }

        else {
            System.out.println("Enregistrement en cours, pas possible de fermer !");
        }
    }
}
