import java.util.Scanner;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
//import javafx.geometry.Pos;

public class Main extends Application {

    private Bibliotheque biblio = Bibliotheque.chargerEtat("etatBibliotheque.txt");

    @Override

    public void start (Stage primaryStage){

        Button btnAjouterLivre = new Button("Ajouter un livre");
        Button btnAfficherLivres = new Button("Afficher les livres");
        Button btnEmprunterLivre = new Button("Emprunter un livre");
        Button btnRendreLivre = new Button("Rendre un livre");



        btnAjouterLivre.setOnAction(e -> {
            Stage stage = new Stage();
            GridPane gridPane = new GridPane();
            gridPane.setVgap(10);  // Espacement vertical entre les composants
            gridPane.setHgap(10);  // Espacement horizontal entre les composants
            gridPane.setPadding(new Insets(10, 10, 10, 10));  // Marge autour du grid

            // Création des Labels et des TextFields
            Label titreLabel = new Label("Titre:");
            TextField titreTextField = new TextField();
            titreTextField.setPromptText("Entrez le titre du livre");

            Label auteurLabel = new Label("Auteur:");
            TextField auteurTextField = new TextField();
            auteurTextField.setPromptText("Entrez le nom de l'auteur");

            Label isbnLabel = new Label("ISBN:");
            TextField isbnTextField = new TextField();
            isbnTextField.setPromptText("Entrez l'ISBN du livre");

            Button btnValider = new Button("Valider");
            btnValider.setOnAction(e1 -> {
                try {
                    livre l = new livre(titreTextField.getText(), auteurTextField.getText(), Integer.parseInt(isbnTextField.getText()), 0);
                    biblio.ajouterLivre(l);
                    biblio.sauvegarderEtat("etatBibliotheque.txt");
                    //livre ajouter avec succes
                    Alert alert = new Alert(AlertType.INFORMATION, "Livre ajouté avec succès");
                    alert.showAndWait();
                    stage.close();
                } catch (NumberFormatException nfe) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Veuillez entrer un nombre valide pour l'ISBN");
                    alert.showAndWait();
                }
            });

            // Ajout des composants au GridPane
            gridPane.add(titreLabel, 0, 0);
            gridPane.add(titreTextField, 1, 0);
            gridPane.add(auteurLabel, 0, 1);
            gridPane.add(auteurTextField, 1, 1);
            gridPane.add(isbnLabel, 0, 2);
            gridPane.add(isbnTextField, 1, 2);
            gridPane.add(btnValider, 1, 3);

            Scene scene = new Scene(gridPane, 500, 200);
            stage.setTitle("Ajouter un nouveau livre");
            stage.setScene(scene);
            stage.show();
        });

        btnAfficherLivres.setOnAction(e -> {
            Stage stage = new Stage();
            VBox vbox = new VBox();
            TextArea textArea = new TextArea();
            textArea.setEditable(false);
            for (livre l : biblio.livres) {
                textArea.appendText("Titre: " + l.getTitre() + "\n");
                textArea.appendText("Auteur: " + l.getAuteur() + "\n");
                textArea.appendText("ISBN: " + l.getISBN() + "\n");
                textArea.appendText("Statut: " + l.getStatut() + "\n\n");
            }
            vbox.getChildren().add(textArea);
            Scene scene = new Scene(vbox, 500, 500);
            stage.setScene(scene);
            stage.show();
        });

        btnEmprunterLivre.setOnAction(e -> {
            Stage stage = new Stage();
            GridPane gridPane = new GridPane();
            gridPane.setVgap(10);  // Espacement vertical entre les composants
            gridPane.setHgap(10);  // Espacement horizontal entre les composants
            gridPane.setPadding(new Insets(10, 10, 10, 10));  // Marge autour du grid

            Label isbnLabel = new Label("Entrer l'ISBN:");
            TextField isbnTextField = new TextField();

            Button btnValider = new Button("Valider");
            btnValider.setOnAction(e1 -> {
                    int k=0;
                    int ISBN = Integer.parseInt(isbnTextField.getText());
                    for(livre l : biblio.livres) {
                        if (l.getISBN() == ISBN) {
                            if (l.getStatut() == 0) {
                                biblio.emprunterLivre(ISBN);
                                Alert alert = new Alert(AlertType.INFORMATION, "Livre emprunté avec succès");
                                alert.showAndWait();
                                k=1;
                            }else{
                                Alert alert = new Alert(AlertType.ERROR, "Livre déjà emprunté");
                                alert.showAndWait();
                                k=1;
                            }
                        }

                    }
                    if(k==0){
                        Alert alert = new Alert(AlertType.ERROR, "Livre non trouvé");
                        alert.showAndWait();
                    }

                    biblio.sauvegarderEtat("etatBibliotheque.txt");
                    stage.close();
            });

            gridPane.add(isbnLabel, 0, 0);
            gridPane.add(isbnTextField, 1, 0);
            gridPane.add(btnValider, 1, 1);

            Scene scene = new Scene(gridPane, 500, 200);
            stage.setTitle("Emprunter un livre");
            stage.setScene(scene);
            stage.show();

        });

        btnRendreLivre.setOnAction(e -> {
            Stage stage = new Stage();
            GridPane gridPane = new GridPane();
            gridPane.setVgap(10);  // Espacement vertical entre les composants
            gridPane.setHgap(10);  // Espacement horizontal entre les composants
            gridPane.setPadding(new Insets(10, 10, 10, 10));  // Marge autour du grid

            Label isbnLabel = new Label("Entrer l'ISBN:");
            TextField isbnTextField = new TextField();

            Button btnValider = new Button("Valider");
            btnValider.setOnAction(e1 -> {
                int k=0;
                int ISBN = Integer.parseInt(isbnTextField.getText());
                for(livre l : biblio.livres) {
                    if (l.getISBN() == ISBN) {
                        if (l.getStatut() == 1) {
                            biblio.rendreLivre(ISBN);
                            Alert alert = new Alert(AlertType.INFORMATION, "Livre rendu avec succès");
                            alert.showAndWait();
                            k=1;
                        }else{
                            Alert alert = new Alert(AlertType.ERROR, "Livre déjà rendu");
                            alert.showAndWait();
                            k=1;
                        }
                    }
                }
                if(k==0){
                    Alert alert = new Alert(AlertType.ERROR, "Livre non trouvé");
                    alert.showAndWait();
                }
                biblio.sauvegarderEtat("etatBibliotheque.txt");
                stage.close();
            });

            gridPane.add(isbnLabel, 0, 0);
            gridPane.add(isbnTextField, 1, 0);
            gridPane.add(btnValider, 1, 1);

            Scene scene = new Scene(gridPane, 500, 200);
            stage.setTitle("Rendre un livre");
            stage.setScene(scene);
            stage.show();
        });

        /*btnAjouterLivre.setOnAction(e -> {
            Stage stage = new Stage();
            VBox vbox = new VBox();
            TextField titre = new TextField("titre");
            TextField auteur = new TextField("Auteur");
            TextField ISBN = new TextField("ISBN");
            Button btnValider = new Button("Valider");
            btnValider.setOnAction(e1 -> {
                livre l = new livre(titre.getText(), auteur.getText(), Integer.parseInt(ISBN.getText()), 0);
                biblio.ajouterLivre(l);
                stage.close();
            });
            vbox.getChildren().addAll(titre, auteur, ISBN, btnValider);
            Scene scene = new Scene(vbox, 500, 500);
            stage.setScene(scene);
            stage.show();
        });*/

        VBox layoutPrincipal = new VBox();

        layoutPrincipal.setAlignment(javafx.geometry.Pos.CENTER);
        layoutPrincipal.setSpacing(10);
        layoutPrincipal.setPadding(new Insets(20, 20, 20, 20));

        btnAjouterLivre.setStyle("-fx-font-size: 14pt; -fx-background-color: #EFAAAA; -fx-padding: 10px;");
        btnAfficherLivres.setStyle("-fx-font-size: 14pt; -fx-background-color: #AAF7EF; -fx-padding: 10px;");
        btnEmprunterLivre.setStyle("-fx-font-size: 14pt; -fx-background-color: #AAEFAA; -fx-padding: 10px;");
        btnRendreLivre.setStyle("-fx-font-size: 14pt; -fx-background-color: #EFAAFA; -fx-padding: 10px;");

        layoutPrincipal.getChildren().addAll(btnAjouterLivre, btnAfficherLivres, btnEmprunterLivre, btnRendreLivre);

        Scene scenePrincipal = new Scene(layoutPrincipal, 500, 500);

        primaryStage.setTitle("Bibliotheque");
        primaryStage.setScene(scenePrincipal);
        primaryStage.show();
    }

    /*public static void main(String[] args) {
        launch(args);
    }*/
}
















/*public class Main {
    public static void main(String[] args) {

       // Bibliotheque biblio = new Bibliotheque();
        Scanner sc = new Scanner(System.in);
        Bibliotheque biblio = Bibliotheque.chargerEtat("etatBibliotheque.txt");
        int choix = 0;
        do {
            System.out.println("1. Ajouter un livre");
            System.out.println("2. Afficher les livres");
            System.out.println("3. Emprunter un livre");
            System.out.println("4. Rendre un livre");
            System.out.println("5. Quitter");
            System.out.println("Choix: ");
            choix = sc.nextInt();
            sc.nextLine();
            switch (choix) {
                case 1:
                    System.out.println("Titre: ");
                    String titre = sc.nextLine();
                    System.out.println("Auteur: ");
                    String auteur = sc.nextLine();
                    System.out.println("ISBN: ");
                    int ISBN = sc.nextInt();
                    sc.nextLine();
                    int statut = 0;
                    livre l = new livre(titre, auteur, ISBN, statut);
                    biblio.ajouterLivre(l);
                    break;
                case 2:
                    biblio.afficherLivres();
                    break;
                case 3:
                    System.out.println("ISBN: ");
                    int ISBNEmprunt = sc.nextInt();
                    sc.nextLine();
                    for (livre l1 : biblio.livres) {
                        if (l1.getISBN() == ISBNEmprunt) {
                            if (l1.getStatut() == 0) {
                                biblio.emprunterLivre(ISBNEmprunt);
                            } else {
                                System.out.println("Livre déjà emprunté");
                            }
                        }
                    }
                    break;
                case 4:
                    System.out.println("ISBN: ");
                    int ISBNRendu = sc.nextInt();
                    sc.nextLine();
                    for (livre l1 : biblio.livres) {
                        if (l1.getISBN() == ISBNRendu) {
                            if (l1.getStatut() == 1) {
                                biblio.rendreLivre(ISBNRendu);
                            } else {
                                System.out.println("Livre déjà rendu // ou probleme ");
                            }
                        }
                    }
                    break;
                case 5:
                    System.out.println("Au revoir!");
                    break;
                default:
                    System.out.println("Choix invalide");
                    break;
            }
        } while (choix != 5);

        biblio.sauvegarderEtat("etatBibliotheque.txt");
    }
}*/