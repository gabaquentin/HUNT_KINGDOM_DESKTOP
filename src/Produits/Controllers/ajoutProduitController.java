package Produits.Controllers;

import Produits.Model.Produits;
import Produits.Services.CrudFournisseur;
import Produits.Services.CrudProduit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import sun.security.provider.MD5;

import java.awt.*;
import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;
import javax.activation.*;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javafx.scene.control.TextField;






public class ajoutProduitController implements Initializable {
    @FXML private TextField nom;
    @FXML private TextField qte;
    @FXML private ComboBox<String> ctg;
    @FXML private TextField prx;
        @FXML private ComboBox<String> frns;
    @FXML private TextField desc;
    @FXML private TextField img;
    @FXML private Button JoindreBtn;
    private ObservableList<String> list = FXCollections.observableArrayList("chasse animal","chasse au fusil","peche","peche SS");
    private ObservableList<Integer> frn = FXCollections.observableArrayList(7);
    private ObservableList<Integer> items = FXCollections.observableArrayList(1, 2, 3);



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ctg.setItems(list);

        CrudFournisseur fr =new CrudFournisseur();
        int size= fr.afficher().size();

        for (int i=0; i<size ;i++){
            frns.getItems().addAll(fr.afficher().get(i).getNomFournisseur());
            //jointure
        }
        //Controle De saisie
        Pattern intPattern = Pattern.compile("-?\\d*");
        UnaryOperator<TextFormatter.Change> filter = change -> {
            if (intPattern.matcher(change.getControlNewText()).matches()) {
                return change;
            }
            else {Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText("Erreur");
                alert.setContentText("Veuillez Verifier Les champs!");
                alert.showAndWait();}
            return null;
        };
        TextFormatter textFormatter = new TextFormatter(filter);
        TextField prx = new TextField();
        prx.setTextFormatter(textFormatter);




    }

    public void vider(){
        nom.clear();
        qte.clear();
        prx.clear();
        img.clear();
        desc.clear();
    }

    public void joindre(javafx.event.ActionEvent event) throws IOException {
        FileChooser fc = new FileChooser();
        File selectedFile = fc.showOpenDialog(null);
        if(selectedFile != null){
            img.setText(selectedFile.getName()); //le chemin absolu de l'image!
        }else {
            System.out.println("file is not valid!");
        }
    }


    public void ajoutProduit(javafx.event.ActionEvent actionEvent) {
        CrudFournisseur fr =new CrudFournisseur();

        String nomProduit= nom.getText();
        int quantite= Integer.parseInt(qte.getText());
        String categorie= ctg.getValue();
        int prix= Integer.parseInt(prx.getText());
        String fournisseur=frns.getValue();
        String description=desc.getText();
        String image=img.getText();

        Produits p=new Produits();
        p.setNomProduit(nomProduit);
        p.setQuantite(quantite);
        p.setCategorie(categorie);
        p.setPrix(prix);
        p.setImage(image);
        p.setFournisseur(fr.find(fournisseur));
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

/*
    public void controleNumber(javafx.scene.input.KeyEvent keyEvent) {
        char c=keyEvent.get();
        if(!(Character.isDigit(c)  || c== KeyEvent.VK_BACK_SPACE) ||c==KeyEvent.VK_DELETE){
            keyEvent.consume();

        }
    }
    */

}