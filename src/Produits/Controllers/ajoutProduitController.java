package Produits.Controllers;

import Produits.Model.Produits;
import Produits.Services.CrudProduit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class ajoutProduitController implements Initializable {
    @FXML private TextField nom;
    @FXML private TextField qte;
    @FXML private ComboBox<String> ctg;
    @FXML private TextField prx;
    @FXML private ComboBox<Integer> frns;
    @FXML private TextField desc;
    @FXML private TextField img;
    private ObservableList<String> list = FXCollections.observableArrayList("chasse animal","chasse au fusil","peche","peche SS");
    private ObservableList<Integer> frn = FXCollections.observableArrayList(7);
    private ObservableList<Integer> items = FXCollections.observableArrayList(1, 2, 3);


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ctg.setItems(list);
        frns.setItems(frn);

    }

    public void vider(){
        nom.clear();
        qte.clear();
        prx.clear();
        img.clear();
        desc.clear();
    }

    public void ajoutProduit(javafx.event.ActionEvent actionEvent) {
        String nomProduit= nom.getText();
        int quantite= Integer.parseInt(qte.getText());
        String categorie= ctg.toString();
        int prix= Integer.parseInt(prx.getText());
        int fournisseur=frns.getValue();
        String description=desc.getText();
        String image=img.getText();

        Produits p=new Produits();
        p.setNomProduit(nomProduit);
        p.setQuantite(quantite);
        p.setCategorie(categorie);
        p.setPrix(prix);
        p.setImage(image);
        p.setFournisseur(fournisseur);
        p.setDescription(description);

        int status = CrudProduit.ajoutP(p);

        if (status>0) {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Succès");
            alert.setHeaderText("Succès");
            alert.setContentText("Produit Ajouté Avec Succès!");
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