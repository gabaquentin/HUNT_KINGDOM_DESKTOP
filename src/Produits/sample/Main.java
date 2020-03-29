package Produits.sample;

import Produits.Controllers.DataSource;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Main extends Application {

    @FXML
    private TextField nomProduit;
    @FXML
    private Spinner quantite;
    @FXML
    private ComboBox categorie;
    @FXML
    private TextField prix;





    @Override
    public void start(Stage primaryStage) throws Exception{

        DataSource ds=DataSource.getInstance();


        Parent root = FXMLLoader.load(getClass().getResource("../../View/AfficheP.fxml"));
        primaryStage.setTitle("Ajout Produit");
        primaryStage.setScene(new Scene(root, 900, 600));
        primaryStage.show();
        //String Libelle= nomProduit.getText();
        /*
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Ajout Avec Succès");
        alert.setContentText("Produit Ajouté avec succès");
        alert.showAndWait();
        */

    }


    public static void main(String[] args) {
        launch(args);
    }
}
