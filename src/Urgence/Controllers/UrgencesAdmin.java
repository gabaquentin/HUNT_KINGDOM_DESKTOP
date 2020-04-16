package Urgence.Controllers;

import Expedition.Services.ExpeditionService;
import Urgence.Model.Urgence;
import Urgence.Services.UrgenceService;

import User.Controllers.Loading;
import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import dataSource.DataSource;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javafx.util.Duration;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UrgencesAdmin implements Initializable {

    private double xOffset = 0;
    private double yOffset = 0;

    @FXML
    private StackPane parent;

    @FXML
    private HBox top;

    @FXML
    private Pane menu;

    @FXML
    private Pane btn_menu;

    @FXML
    private FontAwesomeIcon btn_menubars;

    @FXML
    private Pane btn_menu_exit;

    @FXML
    private FontAwesomeIcon btn_menu_exitbars;

    @FXML
    private Pane maximize;

    @FXML
    private Pane minimize;

    @FXML
    private Pane sp_btn;

    @FXML
    private Pane sp_menu;

    @FXML
    private FontAwesomeIcon sp_r;

    @FXML
    private HBox urgence;

    @FXML
    private HBox expedition;

    @FXML
    private HBox dashboard;

    @FXML
    private AnchorPane body;

    @FXML
    private JFXTreeTableView<Urgencer> urgenceList1;

    @FXML
    private JFXButton btnUrgAction;

    @FXML
    private Pane popupnotif;

    @FXML
    private Pane popupnotifSupTitle;

    @FXML
    private Label popupnotifTitle;

    @FXML
    private Label popupnotifMess;

    @FXML
    private FontAwesomeIcon iconTraiter;

    @FXML
    private FontAwesomeIcon iconItineraire;

    @FXML
    private FontAwesomeIcon iconPosition;

    @FXML
    private JFXTextField filterField;

    @FXML
    private FontAwesomeIcon filterFieldSearch;



    @Override
    public void initialize(URL location, ResourceBundle resources){

         sp_menu.setVisible(false);
        urgence.setDisable(true);
        dashboard.setDisable(false);
        popupnotif.setVisible(false);

        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.5));
        slide.setNode(popupnotif);

        slide.setToY(-200);
        slide.play();

        slide.setOnFinished((e ->{

        } ));
        fillTaleUrgence();

        RotateTransition rotate = new RotateTransition();
        rotate.setAxis(Rotate.Z_AXIS);
        rotate.setByAngle(360);
        rotate.setCycleCount(-1);
        rotate.setDuration(Duration.millis(1000));
        rotate.setAutoReverse(true);
        rotate.setNode(iconTraiter);
        rotate.play();

        RotateTransition rotate1 = new RotateTransition();
        rotate1.setAxis(Rotate.Z_AXIS);
        rotate1.setByAngle(360);
        rotate1.setCycleCount(-1);
        rotate1.setDuration(Duration.millis(1000));
        rotate1.setAutoReverse(true);
        rotate1.setNode(iconItineraire);
        rotate1.play();

        TranslateTransition tt = new TranslateTransition(Duration.millis(500), iconPosition);
        tt.setByY(10);
        tt.setCycleCount(-1);
        tt.setAutoReverse(true);

        tt.play();

    }

    @FXML
    private void showTable(MouseEvent event){
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../../Urgence/View/urgenceAdminTable.fxml"));
                Parent parent = loader.load();

                Stage stage = new Stage();
                stage.setTitle("Urgence Table");
                stage.initStyle(StageStyle.DECORATED);
                stage.setScene(new Scene(parent));
                stage.show();

            } catch (IOException ex) {
                Logger.getLogger(Loading.class.getName()).log(Level.SEVERE, null, ex);
            }

    }

    @FXML
    private void getPosition(MouseEvent event){
        if(urgenceList1.getSelectionModel().getSelectedIndex() != -1) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../../Urgence/View/urgenceAdminPosition.fxml"));
                Parent parent = loader.load();

                UrgenceAdminPosition UAP = (UrgenceAdminPosition) loader.getController();
                UAP.setMap(urgenceList1.getSelectionModel().getSelectedItem().getValue().place_id.getValue(), urgenceList1.getSelectionModel().getSelectedItem().getValue().Utilisateur.getValue(), urgenceList1.getSelectionModel().getSelectedItem().getValue().date.getValue(), urgenceList1.getSelectionModel().getSelectedItem().getValue().etat.getValue());

                Stage stage = new Stage();
                stage.setTitle("Urgence Position");
                stage.initStyle(StageStyle.DECORATED);
                stage.setScene(new Scene(parent));
                stage.show();

            } catch (IOException ex) {
                Logger.getLogger(Loading.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else
        {
            popupnotif.setStyle("-fx-background-color: #F58F69");
            popupnotifSupTitle.setStyle("-fx-background-color: #F58F69; -fx-border-radius: 12; -fx-background-radius: 12");
            popupnotifTitle.setText("OUPS");
            popupnotifTitle.setStyle("-fx-text-fill: #fff;");
            popupnotifMess.setText("Selectionnez une urgence pour cette action");
            popupnotifMess.setStyle("-fx-text-fill: #fff;");
            popupnotif.setVisible(true);

            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.8));
            slide.setNode(popupnotif);

            slide.setToY(0);
            slide.play();

            slide.setOnFinished((e ->{
                TranslateTransition slide1 = new TranslateTransition();
                slide1.setDuration(Duration.seconds(1.5));
                slide1.setNode(popupnotif);

                slide1.setToY(-200);
                slide1.play();

                slide1.setOnFinished((e1 ->{
                    popupnotif.setVisible(false);
                }));


            } ));
        }

    }

    @FXML
    private void setItineraire(MouseEvent event){
        if(urgenceList1.getSelectionModel().getSelectedIndex() != -1) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../../Urgence/View/urgenceAdminItineraire.fxml"));
                Parent parent = loader.load();

                UrgenceAdminItineraire UAI = (UrgenceAdminItineraire) loader.getController();
                UAI.drawRoute(urgenceList1.getSelectionModel().getSelectedItem().getValue().addresse.getValue(), urgenceList1.getSelectionModel().getSelectedItem().getValue().Utilisateur.getValue(), urgenceList1.getSelectionModel().getSelectedItem().getValue().date.getValue(), urgenceList1.getSelectionModel().getSelectedItem().getValue().etat.getValue());

                Stage stage = new Stage();
                stage.setTitle("Urgence Itineraire");
                stage.initStyle(StageStyle.DECORATED);
                stage.setScene(new Scene(parent));
                stage.show();

            } catch (IOException ex) {
                Logger.getLogger(Loading.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else
        {

            popupnotif.setStyle("-fx-background-color: #F58F69");
            popupnotifSupTitle.setStyle("-fx-background-color: #F58F69; -fx-border-radius: 12; -fx-background-radius: 12");
            popupnotifTitle.setText("OUPS");
            popupnotifTitle.setStyle("-fx-text-fill: #fff;");
            popupnotifMess.setText("Selectionnez une urgence pour cette action");
            popupnotifMess.setStyle("-fx-text-fill: #fff;");
            popupnotif.setVisible(true);

            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.8));
            slide.setNode(popupnotif);

            slide.setToY(0);
            slide.play();

            slide.setOnFinished((e ->{
                TranslateTransition slide1 = new TranslateTransition();
                slide1.setDuration(Duration.seconds(1.5));
                slide1.setNode(popupnotif);

                slide1.setToY(-200);
                slide1.play();

                slide1.setOnFinished((e1 ->{
                    popupnotif.setVisible(false);
                }));


            } ));

        }
    }

    public String getPID(){
        return urgenceList1.getSelectionModel().getSelectedItem().getValue().id.getValue();
    }

    private void fillTaleUrgence(){

        JFXTreeTableColumn<Urgencer, String> IdColumn = new JFXTreeTableColumn<Urgencer, String>("Id");
        JFXTreeTableColumn<Urgencer, String> UserColumn = new JFXTreeTableColumn<Urgencer, String>("Utilisateur");
        JFXTreeTableColumn<Urgencer, String> AddressColumn = new JFXTreeTableColumn<Urgencer, String>("Adresse");
        JFXTreeTableColumn<Urgencer, String> AddressNameColumn = new JFXTreeTableColumn<Urgencer, String>("Nom");
        JFXTreeTableColumn<Urgencer, String> LatColumn = new JFXTreeTableColumn<Urgencer, String>("Latitude");
        JFXTreeTableColumn<Urgencer, String> LongColumn = new JFXTreeTableColumn<Urgencer, String>("Longitude");
        JFXTreeTableColumn<Urgencer, String> DescColumn = new JFXTreeTableColumn<Urgencer, String>("Description");
        JFXTreeTableColumn<Urgencer, String> PlusColumn = new JFXTreeTableColumn<Urgencer, String>("Plus");
        JFXTreeTableColumn<Urgencer, String> ExpColumn = new JFXTreeTableColumn<Urgencer, String>("Expedition");
        JFXTreeTableColumn<Urgencer, String> IdpColumn = new JFXTreeTableColumn<Urgencer, String>("Id");
        JFXTreeTableColumn<Urgencer, String> DateColumn = new JFXTreeTableColumn<Urgencer, String>("Date");
        JFXTreeTableColumn<Urgencer, String> EtatColumn = new JFXTreeTableColumn<Urgencer, String>("Etat");
        JFXTreeTableColumn<Urgencer, String> ActionColumn = new JFXTreeTableColumn<Urgencer, String>("Action");

        AddressColumn.getColumns().addAll(AddressNameColumn, IdpColumn);

        IdColumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Urgencer, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Urgencer, String> param) {
                return param.getValue().getValue().id;
            }
        });
        UserColumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Urgencer, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Urgencer, String> param) {
                return param.getValue().getValue().Utilisateur;
            }
        });
        AddressNameColumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Urgencer, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Urgencer, String> param) {
                return param.getValue().getValue().addresse;
            }
        });
        DescColumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Urgencer, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Urgencer, String> param) {
                return param.getValue().getValue().description;
            }
        });
        PlusColumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Urgencer, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Urgencer, String> param) {
                return param.getValue().getValue().plus;
            }
        });
        ExpColumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Urgencer, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Urgencer, String> param) {
                return param.getValue().getValue().expediton;
            }
        });
        IdpColumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Urgencer, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Urgencer, String> param) {
                return param.getValue().getValue().place_id;
            }
        });
        DateColumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Urgencer, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Urgencer, String> param) {
                return param.getValue().getValue().date;
            }
        });
        EtatColumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Urgencer, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Urgencer, String> param) {
                return param.getValue().getValue().etat;
            }
        });
        ActionColumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Urgencer, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Urgencer, String> param) {
                return param.getValue().getValue().Utilisateur;
            }
        });

        ObservableList<Urgencer> list = getUrgenceList();

        final TreeItem<Urgencer> root = new RecursiveTreeItem<Urgencer>(list, RecursiveTreeObject::getChildren);
        urgenceList1.getColumns().setAll(UserColumn, AddressColumn, EtatColumn);
        urgenceList1.setRoot(root);
        urgenceList1.setShowRoot(false);
        urgenceList1.setEditable(true);

        filterField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                urgenceList1.setPredicate(new Predicate<TreeItem<Urgencer>>() {
                    @Override
                    public boolean test(TreeItem<Urgencer> urgencerTreeItem) {
                        Boolean flag = urgencerTreeItem.getValue().id.getValue().toLowerCase().contains(newValue) || urgencerTreeItem.getValue().Utilisateur.getValue().toLowerCase().contains(newValue) || urgencerTreeItem.getValue().etat.getValue().toLowerCase().contains(newValue) || urgencerTreeItem.getValue().date.getValue().toLowerCase().contains(newValue) || urgencerTreeItem.getValue().addresse.getValue().toLowerCase().contains(newValue);
                        return flag;
                    }
                });
            }
        });

    }

    static class  Urgencer extends RecursiveTreeObject<Urgencer> {

        StringProperty id;
        StringProperty expediton;
        StringProperty Utilisateur;
        StringProperty addresse;
        StringProperty description;
        StringProperty plus;
        StringProperty place_id;
        StringProperty date;
        StringProperty etat;

        public Urgencer (String id, String expedition, String Utilisateur, String addresse, String description, String plus, String place_id, String date, String etat){
            this.id = new SimpleStringProperty(id);
            this.expediton = new SimpleStringProperty(expedition);
            this.Utilisateur = new SimpleStringProperty(Utilisateur);
            this.addresse = new SimpleStringProperty(addresse);
            this.description = new SimpleStringProperty(description);
            this.plus = new SimpleStringProperty(plus);
            this.place_id = new SimpleStringProperty(place_id);
            this.date = new SimpleStringProperty(date);
            this.etat = new SimpleStringProperty(etat);
        }

    }


    private ObservableList<Urgencer> getUrgenceList() {
        ObservableList<Urgencer> list = FXCollections.observableArrayList(afficher2());
        return list;
    }

    Connection cnx= DataSource.getInstance().getCnx();

    public List<Urgencer> afficher2() {
        List<Urgencer> list= new ArrayList<>();
        String req="SELECT * FROM urgence ORDER BY etat";
        try {
            PreparedStatement pst=cnx.prepareStatement(req);
            ResultSet rs=pst.executeQuery();
            while (rs.next()){
                ExpeditionService Es = new ExpeditionService();
                if(rs.getInt(2) == 1)
                {
                    if(rs.getString(11).equals("0"))
                    {
                        Urgencer U=new Urgencer(rs.getString(1), "",rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(9),rs.getString(10),"A traiter");
                        list.add(U);
                    }
                    else if(rs.getString(11).equals("1"))
                    {
                        Urgencer U=new Urgencer(rs.getString(1), "",rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(9),rs.getString(10),"Déja traité");
                        list.add(U);
                    }
                }
                else
                {
                    if(rs.getString(11).equals("0"))
                    {
                        Urgencer U=new Urgencer(rs.getString(1), Es.findName(rs.getInt(2)),rs.getString(3),rs.getString(4),rs.getString(5),"",rs.getString(9),rs.getString(10),"A traiter");
                        list.add(U);
                    }
                    else if(rs.getString(11).equals("1"))
                    {
                        Urgencer U=new Urgencer(rs.getString(1), Es.findName(rs.getInt(2)),rs.getString(3),rs.getString(4),rs.getString(5),"",rs.getString(9),rs.getString(10),"Déja traité");
                        list.add(U);
                    }

                }


            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    @FXML
    private void filterFieldAct(MouseEvent event){
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.5));
        slide.setNode(filterFieldSearch);

        slide.setToX(-20);
        slide.play();

        slide.setOnFinished((e ->{
        } ));
    }

    @FXML
    private void showspmenu(MouseEvent event){
        sp_menu.setVisible(true);
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.5));
        slide.setNode(sp_menu);

        slide.setToX(+300);
        slide.play();

        slide.setOnFinished((e ->{
            sp_btn.setDisable(true);
            sp_r.setVisible(false);
        } ));
    }

    @FXML
    private void btnUrgAction(MouseEvent event){

        if(urgenceList1.getSelectionModel().getSelectedIndex() != -1){
            String id = urgenceList1.getSelectionModel().getSelectedItem().getValue().id.getValue();
            String etat = urgenceList1.getSelectionModel().getSelectedItem().getValue().etat.getValue();
            int idInt = Integer.parseInt(id);

            if(etat.equals("A traiter")){

                UrgenceService Us = new UrgenceService();
                Us.traiter(idInt);
                fillTaleUrgence();

                popupnotif.setStyle("-fx-background-color:  #58C47A");
                popupnotifSupTitle.setStyle("-fx-background-color:  #58C47A; -fx-border-radius: 12; -fx-background-radius: 12");
                popupnotifTitle.setText("C'est ok");
                popupnotifTitle.setStyle("-fx-text-fill: #fff;");
                popupnotifMess.setText("L'urgence a été marquée comme traité.");
                popupnotifMess.setStyle("-fx-text-fill: #fff;");
                popupnotif.setVisible(true);

                TranslateTransition slide = new TranslateTransition();
                slide.setDuration(Duration.seconds(0.8));
                slide.setNode(popupnotif);

                slide.setToY(0);
                slide.play();

                slide.setOnFinished((e ->{
                    TranslateTransition slide1 = new TranslateTransition();
                    slide1.setDuration(Duration.seconds(1.5));
                    slide1.setNode(popupnotif);

                    slide1.setToY(-200);
                    slide1.play();

                    slide1.setOnFinished((e1 ->{
                        popupnotif.setVisible(false);
                    }));


                } ));
            }
            else
            {

                popupnotif.setStyle("-fx-background-color: #F58F69");
                popupnotifSupTitle.setStyle("-fx-background-color: #F58F69; -fx-border-radius: 12; -fx-background-radius: 12");
                popupnotifTitle.setText("Nooon");
                popupnotifTitle.setStyle("-fx-text-fill: #fff;");
                popupnotifMess.setText("Désolé mais cette urgence a déja été traité.");
                popupnotifMess.setStyle("-fx-text-fill: #fff;");
                popupnotif.setVisible(true);

                TranslateTransition slide = new TranslateTransition();
                slide.setDuration(Duration.seconds(0.8));
                slide.setNode(popupnotif);

                slide.setToY(0);
                slide.play();

                slide.setOnFinished((e ->{
                    TranslateTransition slide1 = new TranslateTransition();
                    slide1.setDuration(Duration.seconds(1.5));
                    slide1.setNode(popupnotif);

                    slide1.setToY(-200);
                    slide1.play();

                    slide1.setOnFinished((e1 ->{
                        popupnotif.setVisible(false);
                    }));


                } ));
            }

            System.out.println("oui");
            System.out.println(id);
        }
        else
        {
            popupnotif.setStyle("-fx-background-color: #D56062");
            popupnotifSupTitle.setStyle("-fx-background-color: #D56062; -fx-border-radius: 12; -fx-background-radius: 12");
            popupnotifTitle.setText("Erreur");
            popupnotifTitle.setStyle("-fx-text-fill: #fff;");
            popupnotifMess.setText("Veuillez selectionner une ligne pour apporter cette modification");
            popupnotifMess.setStyle("-fx-text-fill: #fff;");
            popupnotif.setVisible(true);

            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.8));
            slide.setNode(popupnotif);

            slide.setToY(0);
            slide.play();

            slide.setOnFinished((e ->{
                TranslateTransition slide1 = new TranslateTransition();
                slide1.setDuration(Duration.seconds(1.5));
                slide1.setNode(popupnotif);

                slide1.setToY(-200);
                slide1.play();

                slide1.setOnFinished((e1 ->{
                    popupnotif.setVisible(false);
                }));


            } ));
            System.out.println("non");
        }


    }

    @FXML
    private void closespmenu(MouseEvent event){
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.5));
        slide.setNode(sp_menu);

        slide.setToX(0);
        slide.play();

        slide.setOnFinished((e ->{
            sp_btn.setDisable(false);
            sp_r.setVisible(true);
            sp_menu.setVisible(false);
        } ));
    }

    public void expedition(MouseEvent event) throws IOException {
        AnchorPane newLoaded = FXMLLoader.load(getClass().getResource("../../Expedition/View/expeditionsAdmin.fxml"));

        body.getChildren().setAll(newLoaded);
    }

    public void urgence(MouseEvent event) throws IOException {
        AnchorPane newLoaded = FXMLLoader.load(getClass().getResource("../../Urgence/View/urgencesAdmin.fxml"));

        body.getChildren().setAll(newLoaded);
    }

    public void dashboard(MouseEvent event) throws IOException {
        AnchorPane newLoaded = FXMLLoader.load(getClass().getResource("../../User/View/dashboardAdmin.fxml"));

        body.getChildren().setAll(newLoaded);
    }

}
