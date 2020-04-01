package Produits.Controllers;

import Produits.Model.Produits;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import static javafx.stage.Stage.*;

public class GestionProduit implements Initializable {
    Connection cnx=DataSource.getInstance().getCnx();

    @FXML private TableView<Produits>table ;
    @FXML private TableColumn<Produits,String>libelle;
    @FXML private TableColumn<Produits,Integer>quantite;
    @FXML private TableColumn<Produits,String>categorie;
    @FXML private TableColumn<Produits,Integer>prix;
    @FXML private TableColumn<Produits,Integer>fournisseur;
    @FXML private TableColumn<Produits,String>description;
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
    @FXML
    private void handleButtonAction (ActionEvent event)throws IOException{

        Parent root = FXMLLoader.load(getClass().getResource("../View/AjoutP.fxml"));
        Scene scene=new Scene(root);

        Stage stage= new Stage();
        stage.setTitle("Ajout Produit");
        stage.setScene(new Scene(root, 900, 600));
        stage.show();


    }




    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void handleButtonAction(javafx.event.ActionEvent actionEvent) {
    }
}
