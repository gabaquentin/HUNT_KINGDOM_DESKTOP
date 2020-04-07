package Expedition.Controllers;

import Expedition.Model.Expedition;
import Expedition.Services.ExpeditionService;
import User.Controllers.Loading;
import User.Services.UserService;
import com.jfoenix.controls.*;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DateCell;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Expeditionn implements Initializable {

    @FXML
    private WebView mapUrgence;

    @FXML
    private JFXButton formUrgenceBtn;

    @FXML
    private JFXButton formUrgenceBtn1;

    @FXML
    private Pane formUrgenceBar;

    @FXML
    private Pane formUrgenceBody;

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
    private JFXTextField nom;

    @FXML
    private JFXDatePicker dateDebut;

    @FXML
    private JFXTimePicker heureDebut;

    @FXML
    private JFXDatePicker dateFin;

    @FXML
    private JFXTimePicker heureFin;

    @FXML
    private JFXTextField destination;

    @FXML
    private JFXTextArea description;

    @FXML
    private JFXButton ajouter;

    @FXML
    private Pane erreur;

    @FXML
    private Pane succes;

    @FXML
    private Label text_erreur;

    @FXML
    private Pane rigthPanel;

    @FXML
    private Pane rigthSticksPanel;

    @FXML
    private Pane urgence;

    @FXML
    private Pane rigthSticksPanel1;

    @Override
    public void initialize(URL location, ResourceBundle resources){

        btn_menu_exit.setVisible(false);
        btn_menu_exitbars.setVisible(false);
        formUrgenceBody.setOpacity(0);
        formUrgenceBody.setVisible(false);
        formUrgenceBtn1.setVisible(false);
        succes.setVisible(false);
        erreur.setVisible(false);
        rigthPanel.setVisible(false);
        rigthSticksPanel1.setVisible(false);

        dateDebut.setDayCellFactory(picker -> new DateCell() {
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                LocalDate today = LocalDate.now();

                setDisable(empty || date.compareTo(today) < 0 );
            }
        });
        dateFin.setDayCellFactory(picker -> new DateCell() {
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                LocalDate today = LocalDate.now();

                setDisable(empty || date.compareTo(today) < 0 );
            }
        });
        UserService Us = new UserService();
        System.out.println(Us.find("venomia"));
        handleDragged();
        WebEngine engine = mapUrgence.getEngine();
        URL url = this.getClass().getResource("../View/mapExpedition.html");
        //engine.load(url.toString());
        engine.load("http://127.0.0.1:8000/emergency/deskExpedition");
    }

    @FXML
    private void urgence(MouseEvent event){
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../../Urgence/View/urgences.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(Loading.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(root);
        Stage stage = new Stage();

        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
        parent.getScene().getWindow().hide();
    }

    @FXML
    private void add_expedition(MouseEvent event) throws Exception {

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat dateFormat2 = new SimpleDateFormat("HH:mm");
        Calendar cal = Calendar.getInstance();

        LocalDate Ddebut = dateDebut.getValue();
        LocalDate Dfin = dateFin.getValue();

        LocalTime Tdebut = heureDebut.getValue();
        LocalTime Tfin = heureFin.getValue();



        if(nom.getText().trim().isEmpty() && destination.getText().trim().isEmpty() && description.getText().trim().isEmpty()){
            text_erreur.setText("Erreur !!! Veuillez verifier que le formulaire est bien saisi");
            erreur.setVisible(true);
        }
        else{
            if(Ddebut != null && Dfin != null && Tdebut != null && Tfin != null)
            {

                if((Ddebut.compareTo(Dfin) < 0) && (Tdebut.compareTo(Tfin) < 0 || Tdebut.compareTo(Tfin) == 0))
                {
                    String debut = Ddebut+"T"+Tdebut;
                    String fin = Dfin+"T"+Tfin;
                    ExpeditionService Es = new ExpeditionService();
                    UserService Us = new UserService();
                    Expedition E = new Expedition(nom.getText(), "0", debut, fin, dateFormat.format(cal.getTime()), description.getText(), destination.getText(), "chasse", Us.find("venomia"));

                    Es.ajouter(E);

                    erreur.setVisible(false);
                    System.out.println("oui");

                    nom.setText("");
                    destination.setText("");
                    description.setText("");
                    dateDebut.setValue(null);
                    dateFin.setValue(null);
                    heureDebut.setValue(null);
                    heureFin.setValue(null);


                    MouseEvent event1 = null;
                    closeformUrgenceBody(event1);

                    succes.setVisible(true);
                    FadeTransition fade = new FadeTransition();
                    fade.setDuration(Duration.millis(6000));
                    fade.setFromValue(0.4);
                    fade.setToValue(1);
                    fade.setCycleCount(2);
                    fade.setAutoReverse(true);
                    fade.setNode(succes);
                    fade.play();

                    fade.setOnFinished((e ->{
                        succes.setVisible(false);
                    } ));
                }
                else if((Ddebut.compareTo(Dfin) == 0) && (Tdebut.compareTo(Tfin) < 0 || Tdebut.compareTo(Tfin) == 0)){

                    String debut = Ddebut+"T"+Tdebut;
                    String fin = Dfin+"T"+Tfin;
                    ExpeditionService Es = new ExpeditionService();
                    UserService Us = new UserService();
                    Expedition E = new Expedition(nom.getText(), "0", debut, fin, dateFormat.format(cal.getTime()), description.getText(), destination.getText(), "chasse", Us.find("venomia"));

                    Es.ajouter(E);

                    erreur.setVisible(false);
                    System.out.println("oui");

                    nom.setText("");
                    destination.setText("");
                    description.setText("");
                    dateDebut.setValue(null);
                    dateFin.setValue(null);
                    heureDebut.setValue(null);
                    heureFin.setValue(null);


                    MouseEvent event1 = null;
                    closeformUrgenceBody(event1);

                    succes.setVisible(true);
                    FadeTransition fade = new FadeTransition();
                    fade.setDuration(Duration.millis(6000));
                    fade.setFromValue(0.4);
                    fade.setToValue(1);
                    fade.setCycleCount(2);
                    fade.setAutoReverse(true);
                    fade.setNode(succes);
                    fade.play();

                    fade.setOnFinished((e ->{
                        succes.setVisible(false);
                    } ));
                }
                else
                {
                    text_erreur.setText("Attention !!! Verifier les dates");
                    erreur.setVisible(true);
                }

            }
            else
            {
                text_erreur.setText("Erreur !!! Veuillez verifier que le formulaire est bien saisi");
                erreur.setVisible(true);
            }

        }


    }

    @FXML
    private void showRigthPanel(MouseEvent event){
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.7));
        slide.setNode(rigthSticksPanel);

        slide.setToX(-200);
        slide.play();

        //rigthPanel.setOpacity(1);
        rigthPanel.setVisible(true);

        slide.setOnFinished((e ->{
            rigthSticksPanel1.setVisible(true);
            rigthSticksPanel.setVisible(false);

        } ));
    }

    @FXML
    private void closeRigthPanel(MouseEvent event){
        rigthSticksPanel.setVisible(true);
        rigthSticksPanel1.setVisible(false);
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.7));
        slide.setNode(rigthSticksPanel);

        slide.setToX(0);
        slide.play();

        //rigthPanel.setOpacity(1);
        rigthPanel.setVisible(false);

        slide.setOnFinished((e ->{

        } ));
    }

    @FXML
    private void showformUrgenceBody(MouseEvent event){
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.7));
        slide.setNode(formUrgenceBody);

        slide.setToY(-50);
        slide.play();

        formUrgenceBody.setOpacity(0.88);
        formUrgenceBody.setVisible(true);
        formUrgenceBtn1.setVisible(true);
        formUrgenceBtn.setVisible(false);

        slide.setOnFinished((e ->{

        } ));
    }

    @FXML
    private void closeformUrgenceBody(MouseEvent event){
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.7));
        slide.setNode(formUrgenceBody);

        slide.setToY(-700);
        slide.play();

        formUrgenceBtn1.setVisible(false);
        formUrgenceBtn.setVisible(true);

        slide.setOnFinished((e ->{
            formUrgenceBody.setOpacity(0);
            formUrgenceBody.setVisible(false);
        } ));
    }

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

