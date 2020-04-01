package Produits.Controllers;

import Produits.Model.Fournisseurs;
import Produits.Model.Produits;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class GestionFournisseur implements Initializable {
    Connection cnx=DataSource.getInstance().getCnx();

    @FXML private TableView<Fournisseurs> tableF ;
    @FXML private TableColumn<Fournisseurs,String> nomFournisseur;
    @FXML private TableColumn<Fournisseurs,Integer>idFournisseur;
    @FXML private TableColumn<Fournisseurs,String>emailFournisseur;
    @FXML private Button AjoutFBtn;
    @FXML private Button ModifFBtn;
    public ObservableList<Fournisseurs> data =FXCollections.observableArrayList();

    @FXML
    public void afficheFournisseur() {
        String req="SELECT * FROM fournisseur";
        try {

            PreparedStatement pst=cnx.prepareStatement(req);
            ResultSet rs=pst.executeQuery();

            while (rs.next()){
                data.add(new Fournisseurs(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getString(4)));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        nomFournisseur.setCellValueFactory(new PropertyValueFactory<Fournisseurs,String>("nomFournisseur"));
        idFournisseur.setCellValueFactory(new PropertyValueFactory<Fournisseurs, Integer>("idFournisseur"));
        emailFournisseur.setCellValueFactory(new PropertyValueFactory<Fournisseurs, String>("emailFournisseur"));
        tableF.setItems(data);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    public void fenetreAjoutF(javafx.event.ActionEvent actionEvent) {
        try {
            Stage stage= new Stage() ;
            Parent root = FXMLLoader.load(getClass().getResource("../View/AjoutFournisseur.fxml"));

            stage.setTitle("Ajout Fournisseur");
            stage.setScene(new Scene(root, 430, 580));
            stage.show();

            AjoutFBtn.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

        public void fenetreModifF(javafx.event.ActionEvent actionEvent) {
        try {
            Stage stage= new Stage() ;
            Parent root = FXMLLoader.load(getClass().getResource("../View/ModifFournisseur.fxml"));

            stage.setTitle("Modification Fournisseur");
            stage.setScene(new Scene(root, 430, 530));
            stage.show();

            ModifFBtn.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
