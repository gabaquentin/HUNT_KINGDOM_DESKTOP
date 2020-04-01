package Produits.Controllers;

import Produits.Model.Produits;
import Produits.Services.CrudProduit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import sun.security.krb5.internal.crypto.Crc32CksumType;

import java.net.URL;
import java.util.ResourceBundle;

public class modifProduitController implements Initializable {
    @FXML
    private TextField idP;
    @FXML
    private TextField nom;
    @FXML
    private TextField qte;
    @FXML
    private ComboBox<String> ctg;
    @FXML
    private TextField prx;
    @FXML
    private ComboBox<Integer> frns;
    @FXML
    private TextField desc;
    @FXML
    private TextField img;
    @FXML
    private Button chercherBtn;
    @FXML
    private Button modifierBtn;
    @FXML
    private Button supprimerBtn;

    private ObservableList<String> list = FXCollections.observableArrayList("chasse animal", "chasse au fusil", "peche", "peche SS");
    private ObservableList<Integer> frn = FXCollections.observableArrayList(7);
    private ObservableList<Integer> items = FXCollections.observableArrayList(1, 2, 3);


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ctg.setItems(list);
        frns.setItems(frn);

    }

    public void chercherProduit(ActionEvent actionEvent) {
        String sid = idP.getText();
        int id = Integer.parseInt(sid);
        Produits p = CrudProduit.chercherP(id);

        nom.setText(p.getNomProduit());
        qte.setText(String.valueOf(p.getQuantite()));
        // ctg.setItems(p.getCategorie());
        prx.setText(String.valueOf(p.getPrix()));
        img.setText(p.getImage());
        //frns.setItems(p.getFournisseur());
        desc.setText(p.getDescription());


    }

    public void modifProduit(ActionEvent actionEvent) {
        String sid = idP.getText();
        int id = Integer.parseInt(sid);
        String txtnomP = nom.getText();
        int txtqte = Integer.parseInt(qte.getText());
        String txtctg = ctg.toString();
        int txtprx = Integer.parseInt(prx.getText());
        String txtimg = img.getText();
        int txtfrn = frns.getValue();
        String txtdsc = desc.getText();

        Produits p = new Produits();
        p.setId(id);
        p.setNomProduit(txtnomP);
        p.setQuantite(txtqte);
        p.setCategorie(txtctg);
        p.setPrix(txtprx);
        p.setImage(txtimg);
        p.setFournisseur(txtfrn);
        p.setDescription(txtdsc);

        int status = CrudProduit.modifP(p);
        if (status > 0) {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Succès");
            alert.setHeaderText("Succès");
            alert.setContentText("Produit Modifié Avec Succès!");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur");
            alert.setContentText("Veuillez Verifier!");
            alert.showAndWait();


        }
    }

    public void supprProduit(ActionEvent actionEvent) {
        String sid = idP.getText();
        int id = Integer.parseInt(sid);
        int status = CrudProduit.supprP(id);
        if (status > 0) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Succès");
            alert.setHeaderText("Succès");
            alert.setContentText("Produit Supprimé Avec Succès!");
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

