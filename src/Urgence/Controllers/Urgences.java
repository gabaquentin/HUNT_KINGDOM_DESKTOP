package Urgence.Controllers;

import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class Urgences implements Initializable {

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


    @Override
    public void initialize(URL location, ResourceBundle resources){

        btn_menu_exit.setVisible(false);
        btn_menu_exitbars.setVisible(false);
        formUrgenceBody.setOpacity(0);
        formUrgenceBody.setVisible(false);
        formUrgenceBtn1.setVisible(false);
        handleDragged();
        WebEngine engine = mapUrgence.getEngine();
        URL url = this.getClass().getResource("../View/mapUrgence.html");
        engine.load(url.toString());
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
