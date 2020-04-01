package Produits.Controllers;

import Produits.Model.Produits;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class GestionProduit implements Initializable {
    Connection cnx=DataSource.getInstance().getCnx();

    @FXML private TableView<Produits>table ;
    @FXML private TableColumn<Produits,String>libelle;
    @FXML private TableColumn<Produits,Integer>quantite;
    @FXML private TableColumn<Produits,String>categorie;
    @FXML private TableColumn<Produits,Integer>prix;
    @FXML private TableColumn<Produits,Integer>fournisseur;
    @FXML private TableColumn<Produits,String>description;
    @FXML private Button AjoutBtn;
    @FXML private Button ModifBtn;
    public ObservableList<Produits> data = FXCollections.observableArrayList();

    @FXML
    public void afficheProduit() {
        String req="SELECT * FROM Produits";
        try {

            PreparedStatement pst=cnx.prepareStatement(req);
            ResultSet rs=pst.executeQuery();

            while (rs.next()){
                data.add(new Produits(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getString(4),rs.getInt(5),rs.getString(6),rs.getInt(7),rs.getString(8)));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        libelle.setCellValueFactory(new PropertyValueFactory<Produits, String>("nomProduit"));
        quantite.setCellValueFactory(new PropertyValueFactory<Produits, Integer>("quantite"));
        categorie.setCellValueFactory(new PropertyValueFactory<Produits, String>("categorie"));
        prix.setCellValueFactory(new PropertyValueFactory<Produits, Integer>("prix"));
        fournisseur.setCellValueFactory(new PropertyValueFactory<Produits, Integer>("fournisseur"));
        description.setCellValueFactory(new PropertyValueFactory<Produits, String>("description"));
        table.setItems(data);
    }








    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    public void fenetreAjout(javafx.event.ActionEvent actionEvent) {
        try {
            Stage stage= new Stage() ;
            Parent root = FXMLLoader.load(getClass().getResource("../View/AjoutP.fxml"));

            stage.setTitle("Ajout Produit");
            stage.setScene(new Scene(root, 430, 580));
            stage.show();

            AjoutBtn.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    public void fenetreModif(ActionEvent actionEvent) {
        try {
            Stage stage= new Stage() ;
            Parent root = FXMLLoader.load(getClass().getResource("../View/ModifPr.fxml"));

            stage.setTitle("Modification Produit");
            stage.setScene(new Scene(root, 430, 580));
            stage.show();

            ModifBtn.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
