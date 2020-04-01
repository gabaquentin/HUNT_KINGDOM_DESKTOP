package Produits.Controllers;

import Produits.Model.Fournisseurs;
import Produits.Model.Produits;
import Produits.Services.CrudFournisseur;
import Produits.Services.CrudProduit;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class modifFournisseurController implements Initializable {
    @FXML private TextField idFrns;
    @FXML private TextField nomF;
    @FXML private TextField idF;
    @FXML private TextField emailF;
    @FXML private Button chercherFBtn;

    public void chercherFournisseur(ActionEvent actionEvent) {
        String sid = idFrns.getText();
        int id = Integer.parseInt(sid);
        Fournisseurs f = CrudFournisseur.chercherF(id);
        nomF.setText(f.getNomFournisseur());
        idF.setText(String.valueOf(f.getIdFournisseur()));
        emailF.setText(f.getEmailFournisseur());


    }
    public void supprFournisseur(ActionEvent actionEvent) {
        String sid = idF.getText();
        int id = Integer.parseInt(sid);
        int status = CrudFournisseur.supprF(id);
        if (status > 0) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Succès");
            alert.setHeaderText("Succès");
            alert.setContentText("Fournisseur Supprimé Avec Succès!");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur");
            alert.setContentText("Veuillez Verifier!");
            alert.showAndWait();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
