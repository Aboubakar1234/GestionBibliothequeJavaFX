import java.io.Serializable;

public class livre implements Serializable {
    private String titre;
    private String auteur;
    private int ISBN;
    private int statut;

    public livre(String titre, String auteur, int ISBN, int statut) {
        this.titre = titre;
        this.auteur = auteur;
        this.ISBN = ISBN;
        this.statut = statut;
    }

    public String getTitre() {
        return titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public int getISBN() {
        return ISBN;
    }

    public int getStatut() {
        return statut;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public void setISBN(int ISBN) {
        this.ISBN = ISBN;
    }

    public void setStatut(int statut) {
        this.statut = statut;
    }

    public void afficher() {
        System.out.println("Titre: " + titre);
        System.out.println("Auteur: " + auteur);
        System.out.println("ISBN: " + ISBN);
        System.out.println("Statut: " + statut);
    }

    public void emprunter() {
        if (statut == 0) {
            statut = 1;
        }
    }

    public void rendre() {
        if (statut == 1) {
            statut = 0;
        }
    }



}
