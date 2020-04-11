package Urgence.Controllers;

import Urgence.Model.Urgence;
import User.Controllers.Loading;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.animation.TranslateTransition;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
    private JFXTreeTableView<Urgence> urgence_table;

    @FXML
    private HBox dashboard;

    @FXML
    private AnchorPane body;

    @Override
    public void initialize(URL location, ResourceBundle resources){

         sp_menu.setVisible(false);
        urgence.setDisable(true);
        dashboard.setDisable(false);
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

    public void expedition(MouseEvent event) throws IOException {
        AnchorPane newLoaded = FXMLLoader.load(getClass().getResource("../../Expedition/View/expeditionsAdmin.fxml"));

        body.getChildren().setAll(newLoaded);
    }

    public void urgence(MouseEvent event) throws IOException {
        VBox newLoaded = FXMLLoader.load(getClass().getResource("../../Urgence/View/urgencesAdmin.fxml"));

        body.getChildren().setAll(newLoaded);
    }

    public void dashboard(MouseEvent event) throws IOException {
        VBox newLoaded = FXMLLoader.load(getClass().getResource("../../User/View/dashboardAdmin.fxml"));

        body.getChildren().setAll(newLoaded);
    }

}
