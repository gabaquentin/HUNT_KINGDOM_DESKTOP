package User.Controllers;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class Accueil implements Initializable {

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

    @Override
    public void initialize(URL location, ResourceBundle resources){

        btn_menu_exit.setVisible(false);
        btn_menu_exitbars.setVisible(false);
        minimize.setVisible(false);
        handleDragged();
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
