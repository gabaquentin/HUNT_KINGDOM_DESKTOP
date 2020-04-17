package Produits.Controllers;

import Produits.Model.Produits;
import Produits.Services.CrudProduit;
import User.Controllers.Loading;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GestionProduit implements Initializable {
    Connection cnx=DataSource.getInstance().getCnx();


    private double xOffset = 0;
    private double yOffset = 0;

    @FXML private TableView<Produits>table ;
    @FXML private TableColumn<Produits,String>libelle;
    @FXML private TableColumn<Produits,Integer>quantite;
    @FXML private TableColumn<Produits,String>categorie;
    @FXML private TableColumn<Produits,Integer>prix;
    @FXML private TableColumn<Produits,Integer>fournisseur;
    @FXML private TableColumn<Produits,String>description;
    @FXML private Button AjoutBtn;
    @FXML private Button StatBtn;


    //Integration

    @FXML private StackPane parent;
    @FXML private HBox top;
    @FXML private Pane menu;
    @FXML private Pane btn_menu;
    @FXML private FontAwesomeIcon btn_menubars;
    @FXML private Pane btn_menu_exit;
    @FXML private FontAwesomeIcon btn_menu_exitbars;
    @FXML private Pane maximize;
    @FXML private Pane minimize;



    /////////////


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

        btn_menu_exit.setVisible(false);
        btn_menu_exitbars.setVisible(false);
        minimize.setVisible(false);
        handleDragged();
        table.setEditable(true);
        libelle.setCellFactory(TextFieldTableCell.forTableColumn());
        //quantite.setCellFactory(TextFieldTableCell.forTableColumn());
        categorie.setCellFactory(TextFieldTableCell.forTableColumn());
        description.setCellFactory(TextFieldTableCell.forTableColumn());


    }

    public void fenetreAjout(javafx.event.ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/AjoutP.fxml"));
            Parent parent = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Ajout Produit");
            stage.setScene(new Scene(parent, 430, 580));
            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(Loading.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void onEditChanged(TableColumn.CellEditEvent<Produits,String> event) throws SQLException {
        Produits p = table.getSelectionModel().getSelectedItem();
        p.setNomProduit(event.getNewValue());
        CrudProduit ecr = new CrudProduit();
        //ecr.modifP(p);
        ecr.updatetab(p);


    }

    @FXML
    public void statistique(ActionEvent actionEvent) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/Stat.fxml"));
            Parent parent = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Statistiques Produit");
            stage.setScene(new Scene(parent, 600, 482));
            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(Loading.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    @FXML
    private void supprimer(ActionEvent event) {
        CrudProduit ecr = new CrudProduit();
        ecr.supprP(table.getSelectionModel().getSelectedItem().getId());
        Alert dialogC = new Alert(Alert.AlertType.CONFIRMATION);
        dialogC.setTitle("A confirmation dialog-box");
        dialogC.setHeaderText(null);
        dialogC.setContentText("Voulez vous supprimer le Produit?");
        Optional<ButtonType> answer = dialogC.showAndWait();
        if (answer.get() == ButtonType.OK) {

            table.getItems().removeAll(table.getSelectionModel().getSelectedItem());}
        else {
            System.out.println("User chose Cancel or closed the dialog-box");
        }
    }

    //****************************************************//////////////
    @FXML
    private void showmenu(MouseEvent event){
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.7));
        slide.setNode(menu);

        slide.setToX(294);
        slide.play();

        btn_menu_exit.setVisible(true);
        btn_menu_exitbars.setVisible(true);
        btn_menu.setVisible(false);
        btn_menubars.setVisible(false);

        slide.setOnFinished((e ->{

        } ));
    }

    @FXML
    private void hidemenu(MouseEvent event){
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.7));
        slide.setNode(menu);

        slide.setToX(0);
        slide.play();

        btn_menu_exit.setVisible(false);
        btn_menu_exitbars.setVisible(false);
        btn_menu.setVisible(true);
        btn_menubars.setVisible(true);

        slide.setOnFinished((e ->{

        } ));
    }

    @FXML
    private void handleClose(MouseEvent event){
        Stage s = (Stage) ((Node)event.getSource()).getScene().getWindow();
        s.close();
    }

    @FXML
    private void handleMin(MouseEvent event){
        Stage s = (Stage) ((Node)event.getSource()).getScene().getWindow();
        s.setIconified(true);
    }

    @FXML
    private void handleMax(MouseEvent event){
        Stage s = (Stage) ((Node)event.getSource()).getScene().getWindow();
        s.setMaximized(true);
        minimize.setVisible(true);
        maximize.setVisible(false);
    }

    @FXML
    private void handleMinimize(MouseEvent event){
        Stage s = (Stage) ((Node)event.getSource()).getScene().getWindow();
        s.setMaximized(false);
        minimize.setVisible(false);
        maximize.setVisible(true);
    }

    private void handleDragged(){
        top.setOnMousePressed((event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        }));
        top.setOnMouseDragged((event -> {
            Stage s = (Stage) ((Node)event.getSource()).getScene().getWindow();
            s.setX(event.getScreenX() - xOffset);
            s.setY(event.getScreenY() - yOffset);
            s.setOpacity(0.8f);
        }));
        top.setOnDragDone((event -> {
            Stage s = (Stage) ((Node)event.getSource()).getScene().getWindow();
            s.setOpacity(1.0f);
        }));
        top.setOnMouseReleased((event -> {
            Stage s = (Stage) ((Node)event.getSource()).getScene().getWindow();
            s.setOpacity(1.0f);
        }));
    }


}
