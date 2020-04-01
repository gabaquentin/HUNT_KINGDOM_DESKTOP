package Produits.Controllers;

import Produits.Model.Fournisseurs;
import Produits.Model.Produits;
import Produits.Services.CrudFournisseur;
import Produits.Services.CrudProduit;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ajoutFournisseurController implements Initializable {
    @FXML private TextField nomF;
    @FXML private TextField idF;
    @FXML private TextField emailF;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void vider(){
        nomF.clear();
        idF.clear();
        emailF.clear();
    }


    public void ajoutFournisseur(javafx.event.ActionEvent actionEvent) {
        String nomFournisseur= nomF.getText();
        int idFournisseur= Integer.parseInt(idF.getText());
        String emailFournisseur= emailF.getText();


        Fournisseurs f=new Fournisseurs();
        f.setNomFournisseur(nomFournisseur);
        f.setIdFournisseur(idFournisseur);
        f.setEmailFournisseur(emailFournisseur);

        int status = CrudFournisseur.ajoutF(f);

        if (status>0) {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Succès");
            alert.setHeaderText("Succès");
            alert.setContentText("Fournisseur Ajouté Avec Succès!");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur");
            alert.setContentText("Veuillez Verifier!");
            alert.showAndWait();

        }
    }
}
