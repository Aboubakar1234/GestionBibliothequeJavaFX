import java.util.ArrayList;
import java.io.*;

public class Bibliotheque implements Serializable {

    public ArrayList<livre> livres = new ArrayList<livre>();

    public void ajouterLivre(livre l) {
        livres.add(l);
    }

    public void afficherLivres() {
        for (int i = 0; i < livres.size(); i++) {
            System.out.println("Livre " + (i + 1));
            livres.get(i).afficher();
        }

        /*for (livre l : livres) {
            l.afficher();
        }*/
    }

    public void emprunterLivre(int ISBN) {
        for (livre l : livres) {
            if (l.getISBN() == ISBN) {
                l.emprunter();
            }
        }
    }

    public void rendreLivre(int ISBN) {
        for (livre l : livres) {
            if (l.getISBN() == ISBN) {
                l.rendre();
            }
        }
    }

    public void sauvegarderEtat(String nomFichier) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(nomFichier))) {
            out.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Bibliotheque chargerEtat(String nomFichier) {
        Bibliotheque bibliotheque = null;
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(nomFichier))) {
            bibliotheque = (Bibliotheque) in.readObject();
        } catch (FileNotFoundException e) {
            bibliotheque = new Bibliotheque(); // Crée une nouvelle bibliothèque si aucun fichier trouvé
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return bibliotheque;
    }


}
