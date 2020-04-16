package Expedition.Controllers;

import Expedition.Model.Expedition;
import Urgence.Controllers.UrgenceAdminItineraire;
import Urgence.Controllers.UrgencesAdmin;
import User.Controllers.Loading;
import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.sun.org.apache.xpath.internal.functions.FuncSubstring;
import dataSource.DataSource;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExpeditionsAdmin implements Initializable {

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
    private JFXTextField filterField;

    @FXML
    private FontAwesomeIcon filterFieldSearch;

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
    private JFXTreeTableView<Expeditionr> expeditionList1;

    @FXML
    private JFXProgressBar progress;

    @FXML
    private  Pane progressPane;

    @FXML
    private Label progressPercent;


    @Override
    public void initialize(URL location, ResourceBundle resources){

        sp_menu.setVisible(false);
        urgence.setDisable(false);
        expedition.setDisable(true);
        dashboard.setDisable(false);
        popupnotif.setVisible(false);
        progressPane.setVisible(false);

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

        expeditionList1.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                DateFormat dateFormat2 = new SimpleDateFormat("HH:mm");
                Calendar cal = Calendar.getInstance();

                Date dateNow = null;
                try {
                    dateNow = dateFormat.parse(dateFormat.format(cal.getTime())+" "+dateFormat2.format(cal.getTime()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Date dateDebut = null;
                try {
                    dateDebut = dateFormat.parse(expeditionList1.getSelectionModel().getSelectedItem().getValue().dateDebut.getValue().substring(0, 10)+" "+expeditionList1.getSelectionModel().getSelectedItem().getValue().dateDebut.getValue().substring(11));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                Date dateFin = null;
                try {
                    dateFin = dateFormat.parse(expeditionList1.getSelectionModel().getSelectedItem().getValue().dateFin.getValue().substring(0, 10)+" "+expeditionList1.getSelectionModel().getSelectedItem().getValue().dateFin.getValue().substring(11));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                Timestamp tsdd = new Timestamp(dateDebut.getTime());
                Timestamp tsdf = new Timestamp(dateFin.getTime());
                Timestamp tsdn = new Timestamp(dateNow.getTime());
                System.out.println("DDebut "+tsdd.getTime());
                System.out.println("DFin "+tsdf.getTime());
                System.out.println("DNow "+tsdn.getTime());
                Double Progress = (double)(100 - (((tsdf.getTime() - (tsdn.getTime() + 3600)) * 100)/ (tsdf.getTime() - tsdd.getTime())));
                progressPane.setVisible(true);
                if(Progress > 100){
                    progressPercent.setText("100%");
                    progress.setProgress(Progress/100);
                }
                else if(Progress < 0)
                {
                    progressPercent.setText("en attente");
                    progress.setProgress(Progress/100);
                }
                else
                {
                    progressPercent.setText(Progress+"%");
                    progress.setProgress(Progress/100);
                }
            }
        });
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

    @FXML
    private void showZar(MouseEvent event){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../Expedition/View/expeditionAdminZar.fxml"));
            Parent parent = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Expedition Zones a risque");
            stage.initStyle(StageStyle.DECORATED);
            stage.setScene(new Scene(parent));
            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(Loading.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    public void showTable(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../Expedition/View/expeditionAdminTable.fxml"));
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

    public void filterFieldAct(MouseEvent event) {
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.5));
        slide.setNode(filterFieldSearch);

        slide.setToX(-20);
        slide.play();

        slide.setOnFinished((e ->{
        } ));
    }

    static class  Expeditionr extends RecursiveTreeObject<Expeditionr> {

        StringProperty id;
        StringProperty nom;
        StringProperty statut;
        StringProperty dateDebut;
        StringProperty dateFin;
        StringProperty date;
        StringProperty message;
        StringProperty lieux;
        StringProperty type;
        StringProperty utilisateur;

        public  Expeditionr (String id, String nom, String statut, String dateDebut, String dateFin, String date, String message, String lieux, String type, String utilisateur){
            this.id = new SimpleStringProperty(id);
            this.nom = new SimpleStringProperty(nom);
            this.utilisateur = new SimpleStringProperty(utilisateur);
            this.statut = new SimpleStringProperty(statut);
            this.dateDebut = new SimpleStringProperty(dateDebut);
            this.dateFin = new SimpleStringProperty(dateFin);
            this.message = new SimpleStringProperty(message);
            this.date = new SimpleStringProperty(date);
            this.lieux = new SimpleStringProperty(lieux);
            this.type = new SimpleStringProperty(type);
        }

    }

    Connection cnx= DataSource.getInstance().getCnx();
    public List<Expeditionr> afficherExp() {
        List<Expeditionr> list = new ArrayList<>();
        String req = "SELECT * FROM expedition";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {

                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                DateFormat dateFormat2 = new SimpleDateFormat("HH:mm");
                Calendar cal = Calendar.getInstance();

                Date dateNow = null;
                try {
                    dateNow = dateFormat.parse(dateFormat.format(cal.getTime())+" "+dateFormat2.format(cal.getTime()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Date dateDebut = null;
                try {
                    dateDebut = dateFormat.parse(rs.getString(4).substring(0, 10)+" "+rs.getString(4).substring(11));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                Date dateFin = null;
                try {
                    dateFin = dateFormat.parse(rs.getString(5).substring(0, 10)+" "+rs.getString(5).substring(11));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                Timestamp tsdd = new Timestamp(dateDebut.getTime());
                Timestamp tsdf = new Timestamp(dateFin.getTime());
                Timestamp tsdn = new Timestamp(dateNow.getTime());
                Double Progress = (double)(100 - (((tsdf.getTime() - (tsdn.getTime() + 3600)) * 100)/ (tsdf.getTime() - tsdd.getTime())));

                if((tsdd.getTime() - (tsdn.getTime()+3600)) > 0){

                    Expeditionr E = new Expeditionr(rs.getString(1),rs.getString(2), "en attente", rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10));
                    list.add(E);

                }
                else if((tsdd.getTime() - (tsdn.getTime() + 3600)) < 0){
                    if((tsdf.getTime() - (tsdn.getTime() + 3600)) > 0){
                        Expeditionr E = new Expeditionr(rs.getString(1),rs.getString(2), Progress+"% Terminé", rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10));
                        list.add(E);
                    }
                    else if((tsdf.getTime() - (tsdn.getTime() + 3600)) < 0){
                        Expeditionr E = new Expeditionr(rs.getString(1),rs.getString(2), "Terminé", rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10));
                        list.add(E);
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    private ObservableList<Expeditionr> getExpeditionList() {
        ObservableList<Expeditionr> list = FXCollections.observableArrayList(afficherExp());
        return list;
    }

    private void fillTaleUrgence(){

        JFXTreeTableColumn<Expeditionr, String> idColumn = new JFXTreeTableColumn<Expeditionr, String>("Id");
        JFXTreeTableColumn<Expeditionr, String> nomColumn = new JFXTreeTableColumn<Expeditionr, String>("Nom");
        JFXTreeTableColumn<Expeditionr, String> statutColumn = new JFXTreeTableColumn<Expeditionr, String>("Statut");
        JFXTreeTableColumn<Expeditionr, String> dateDebutColumn = new JFXTreeTableColumn<Expeditionr, String>("Debut");
        JFXTreeTableColumn<Expeditionr, String> dateFinColumn = new JFXTreeTableColumn<Expeditionr, String>("Fin");
        JFXTreeTableColumn<Expeditionr, String> dateColumn = new JFXTreeTableColumn<Expeditionr, String>("Date");
        JFXTreeTableColumn<Expeditionr, String> messageColumn = new JFXTreeTableColumn<Expeditionr, String>("Message");
        JFXTreeTableColumn<Expeditionr, String> lieuxColumn = new JFXTreeTableColumn<Expeditionr, String>("Lieux");
        JFXTreeTableColumn<Expeditionr, String> typeColumn = new JFXTreeTableColumn<Expeditionr, String>("Type");
        JFXTreeTableColumn<Expeditionr, String> userColumn = new JFXTreeTableColumn<Expeditionr, String>("Utilisateur");
        JFXTreeTableColumn<Expeditionr, String> periodeColumn = new JFXTreeTableColumn<Expeditionr, String>("Periode");

        periodeColumn.getColumns().addAll(dateDebutColumn, dateFinColumn);

        idColumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Expeditionr, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Expeditionr, String> param) {
                return param.getValue().getValue().id;
            }
        });
        statutColumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Expeditionr, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Expeditionr, String> param) {
                return param.getValue().getValue().statut;
            }
        });
        nomColumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Expeditionr, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Expeditionr, String> param) {
                return param.getValue().getValue().nom;
            }
        });
        dateDebutColumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Expeditionr, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Expeditionr, String> param) {
                return param.getValue().getValue().dateDebut;
            }
        });
        dateFinColumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Expeditionr, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Expeditionr, String> param) {
                return param.getValue().getValue().dateFin;
            }
        });
        dateColumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Expeditionr, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Expeditionr, String> param) {
                return param.getValue().getValue().date;
            }
        });
        messageColumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Expeditionr, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Expeditionr, String> param) {
                return param.getValue().getValue().message;
            }
        });
        lieuxColumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Expeditionr, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Expeditionr, String> param) {
                return param.getValue().getValue().lieux;
            }
        });
        typeColumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Expeditionr, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Expeditionr, String> param) {
                return param.getValue().getValue().type;
            }
        });
        userColumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Expeditionr, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Expeditionr, String> param) {
                return param.getValue().getValue().utilisateur;
            }
        });

        ObservableList<Expeditionr> list = getExpeditionList();

        final TreeItem<Expeditionr> root = new RecursiveTreeItem<Expeditionr>(list, RecursiveTreeObject::getChildren);
        expeditionList1.getColumns().setAll(userColumn, periodeColumn, statutColumn);
        expeditionList1.setRoot(root);
        expeditionList1.setShowRoot(false);
        expeditionList1.setEditable(true);

        filterField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                expeditionList1.setPredicate(new Predicate<TreeItem<Expeditionr>>() {
                    @Override
                    public boolean test(TreeItem<Expeditionr> urgencerTreeItem) {
                        Boolean flag = urgencerTreeItem.getValue().id.getValue().toLowerCase().contains(newValue) || urgencerTreeItem.getValue().utilisateur.getValue().toLowerCase().contains(newValue) || urgencerTreeItem.getValue().statut.getValue().toLowerCase().contains(newValue) || urgencerTreeItem.getValue().date.getValue().toLowerCase().contains(newValue) || urgencerTreeItem.getValue().dateDebut.getValue().toLowerCase().contains(newValue);
                        return flag;
                    }
                });
            }
        });

    }
}
