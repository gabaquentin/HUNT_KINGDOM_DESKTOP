package User.Controllers;
import java.util.ResourceBundle;
import java.net.URL;

import Expedition.Services.ExpeditionService;
import Urgence.Model.Urgence;
import Urgence.Services.UrgenceService;
import User.Model.User;
import User.Services.UserService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;


public class Controller implements Initializable {

    private double xOffset = 0;
    private double yOffset = 0;


    @FXML
    private StackPane parent;

    @FXML
    private HBox top;

    @FXML
    private AnchorPane layer1;
    /*
    Layer 1
    Connexion
     */

    @FXML
    private ImageView la1_img1;

    @FXML
    private Text la1_text1;

    @FXML
    private Text la1_text11;

    @FXML
    private JFXButton la1_btn1;

        /*
    Layer 1
    Inscription
     */

    @FXML
    private ImageView la1_img2;

    @FXML
    private Text la1_text2;

    @FXML
    private JFXButton la1_btn2;


    @FXML
    private AnchorPane layer2;
    /*
    Layer 2
    Connexion
     */

    @FXML
    private Text la2_text1;

    @FXML
    private Text la2_text11;

    @FXML
    private JFXTextField la2_textf_user1;

    @FXML
    private JFXTextField la2_textf_pass1;

    @FXML
    private Label la2_lab1;

    @FXML
    private JFXButton la2_btn1;

    /*
    Layer 2
    Inscription
     */

    @FXML
    private Text la2_text2;

    @FXML
    private Text la2_text22;

    @FXML
    private JFXTextField la2_textf_nom2;

    @FXML
    private JFXTextField la2_textf_prenom2;

    @FXML
    private JFXTextField la2_textf_email2;

    @FXML
    private JFXTextField la2_textf_tel2;

    @FXML
    private JFXTextField la2_textf_addr2;

    @FXML
    private JFXTextField la2_textf_user2;

    @FXML
    private JFXTextField la2_textf_pass2;

    @FXML
    private JFXTextField la2_textf_pass22;

    @FXML
    private JFXComboBox<String> la2_textf_dom2;

    @FXML
    private JFXTextField la2_textf_auth2;

    @FXML
    private JFXButton la2_btn2;


    @Override
    public void initialize(URL location, ResourceBundle resources) {


                /*
    Layer 1
    Inscription
     */

        la1_img2.setVisible(false);

        la1_text2.setVisible(false);

        la1_btn2.setVisible(false);

    /*
    Layer 2
    Inscription
     */

        la2_text2.setVisible(false);

        la2_text22.setVisible(false);

        la2_textf_nom2.setVisible(false);

        la2_textf_prenom2.setVisible(false);

        la2_textf_email2.setVisible(false);

        la2_textf_tel2.setVisible(false);

        la2_textf_addr2.setVisible(false);

        la2_textf_user2.setVisible(false);

        la2_textf_pass2.setVisible(false);

        la2_textf_pass22.setVisible(false);

        la2_textf_dom2.setVisible(false);

        final ObservableList<String> roles = FXCollections.observableArrayList("chasse", "peche");

        la2_textf_dom2.setItems(roles);

        la2_textf_auth2.setVisible(false);

        la2_btn2.setVisible(false);
    }

    @FXML
    private void btn_signup_la1(MouseEvent event){
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.7));
        slide.setNode(layer2);

        slide.setToX(0);
        slide.play();

        layer1.setTranslateX(0);

            /*
    Layer 1
    Connexion
     */

        la1_img1.setVisible(false);

        la1_text1.setVisible(false);

        la1_text11.setVisible(false);

        la1_btn1.setVisible(false);

            /*
    Layer 2
    Connexion
     */

        la2_text1.setVisible(false);

        la2_text11.setVisible(false);

        la2_textf_user1.setVisible(false);

        la2_textf_pass1.setVisible(false);

        la2_lab1.setVisible(false);

        la2_btn1.setVisible(false);

        /*
    Layer 1
    Inscription
     */

        la1_img2.setVisible(true);

        la1_text2.setVisible(true);

        la1_btn2.setVisible(true);

    /*
    Layer 2
    Inscription
     */

        la2_text2.setVisible(true);

        la2_text22.setVisible(true);

        la2_textf_nom2.setVisible(true);

        la2_textf_prenom2.setVisible(true);

        la2_textf_email2.setVisible(true);

        la2_textf_tel2.setVisible(true);

        la2_textf_addr2.setVisible(true);

        la2_textf_user2.setVisible(true);

        la2_textf_pass2.setVisible(true);

        la2_textf_pass22.setVisible(true);

        la2_textf_dom2.setVisible(true);

        la2_textf_auth2.setVisible(true);

        la2_btn2.setVisible(true);

        slide.setOnFinished((e ->{

        } ));
    }

    @FXML
    private void btn_signin_la1(MouseEvent event){

        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.7));
        slide.setNode(layer2);

        slide.setToX(557);
        slide.play();

        layer1.setTranslateX(-557);

            /*
    Layer 1
    Connexion
     */

        la1_img1.setVisible(true);

        la1_text1.setVisible(true);

        la1_text11.setVisible(true);

        la1_btn1.setVisible(true);

            /*
    Layer 2
    Connexion
     */

        la2_text1.setVisible(true);

        la2_text11.setVisible(true);

        la2_textf_user1.setVisible(true);

        la2_textf_pass1.setVisible(true);

        la2_lab1.setVisible(true);

        la2_btn1.setVisible(true);

        /*
    Layer 1
    Inscription
     */

        la1_img2.setVisible(false);

        la1_text2.setVisible(false);

        la1_btn2.setVisible(false);

    /*
    Layer 2
    Inscription
     */

        la2_text2.setVisible(false);

        la2_text22.setVisible(false);

        la2_textf_nom2.setVisible(false);

        la2_textf_prenom2.setVisible(false);

        la2_textf_email2.setVisible(false);

        la2_textf_tel2.setVisible(false);

        la2_textf_addr2.setVisible(false);

        la2_textf_user2.setVisible(false);

        la2_textf_pass2.setVisible(false);

        la2_textf_pass22.setVisible(false);

        la2_textf_dom2.setVisible(false);

        la2_textf_auth2.setVisible(false);

        la2_btn2.setVisible(false);

        slide.setOnFinished((e ->{

        } ));

    }

    @FXML
    private void btn_signup_la2(MouseEvent event) throws Exception {

        if(la2_textf_nom2.getText().equals("") && la2_textf_prenom2.getText().equals("") && la2_textf_email2.getText().equals("") && la2_textf_tel2.getText().equals("") && la2_textf_addr2.getText().equals("") && la2_textf_auth2.getText().equals("") && la2_textf_user2.getText().equals("") && la2_textf_pass2.getText().equals("") && la2_textf_pass22.getText().equals(""))
        {
            la1_text2.setText("Veuillez remplir tous les champs du formulaire");

        }
        else{

            if(la2_textf_pass2.getText().equals(la2_textf_pass22.getText()))
            {

                /*
                UserService Us = new UserService();

                User U = new User(la2_textf_nom2.getText(),la2_textf_prenom2.getText(),la2_textf_email2.getText(),la2_textf_tel2.getText(),la2_textf_addr2.getText(),la2_textf_auth2.getText(),la2_textf_user2.getText(),la2_textf_pass2.getText(),la2_textf_dom2.getValue(),"0");

                Us.connexion(U);
                 */
                

                layer1.setDisable(true);
                la1_text2.setText("Consultez l'email envoyé a l'addresse "+la2_textf_email2.getText() +" .  Cet email contient le lien d'activation de votre compte ensuite vous pouriez vous connecter.");
            }
            else{

                la1_text2.setText("Les mots de passe ne sont pas identiques");

            }

        }


    }

    @FXML
    private void btn_signin_la2(MouseEvent event){

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
        s.setFullScreen(true);
    }

    @FXML
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
