package User.Controllers;
import java.io.IOException;
import java.util.ResourceBundle;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Expedition.Services.ExpeditionService;
import Urgence.Model.Urgence;
import Urgence.Services.UrgenceService;
import User.Model.User;
import User.Services.UserService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
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
    private JFXPasswordField la2_textf_pass1;

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
    private JFXPasswordField la2_textf_pass2;

    @FXML
    private JFXPasswordField la2_textf_pass22;

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

        layer1.setDisable(false);

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
        la1_text2.setText("Vous avez deja un compte ?");

        la1_btn2.setVisible(false);

    /*
    Layer 2
    Inscription
     */

        la2_text2.setVisible(false);

        la2_text22.setVisible(false);

        la2_textf_nom2.setVisible(false);
        la2_textf_nom2.setText("");

        la2_textf_prenom2.setVisible(false);
        la2_textf_prenom2.setText("");

        la2_textf_email2.setVisible(false);
        la2_textf_email2.setText("");

        la2_textf_tel2.setVisible(false);
        la2_textf_tel2.setText("");

        la2_textf_addr2.setVisible(false);
        la2_textf_addr2.setText("");

        la2_textf_user2.setVisible(false);
        la2_textf_user2.setText("");

        la2_textf_pass2.setVisible(false);
        la2_textf_pass2.setText("");

        la2_textf_pass22.setVisible(false);
        la2_textf_pass22.setText("");

        la2_textf_dom2.setVisible(false);

        la2_textf_auth2.setVisible(false);
        la2_textf_auth2.setText("");

        la2_btn2.setVisible(false);

        slide.setOnFinished((e ->{

        } ));

    }

    @FXML
    private void btn_signup_la2(MouseEvent event) throws Exception {

        UserService Us = new UserService();

        if(la2_textf_nom2.getText().equals("") && la2_textf_prenom2.getText().equals("") && la2_textf_email2.getText().equals("") && la2_textf_tel2.getText().equals("") && la2_textf_addr2.getText().equals("") && la2_textf_auth2.getText().equals("") && la2_textf_user2.getText().equals("") && la2_textf_pass2.getText().equals("") && la2_textf_pass22.getText().equals(""))
        {
            la1_text2.setText("Veuillez remplir tous les champs du formulaire");

        }
        else{

            if(la2_textf_pass2.getText().equals(la2_textf_pass22.getText()))
            {
                if(validateEmail(la2_textf_email2)) {
                    if(Us.findEmail(la2_textf_email2.getText()) == 0)
                    {

                        if (validateNumber(la2_textf_tel2)) {

                            if(Us.find(la2_textf_user2.getText()) == 0)
                            {


                User U = new User(la2_textf_nom2.getText(),la2_textf_prenom2.getText(),la2_textf_email2.getText(),la2_textf_tel2.getText(),la2_textf_addr2.getText(),la2_textf_auth2.getText(),la2_textf_user2.getText(),la2_textf_pass2.getText(),la2_textf_dom2.getValue(),"0");

                                if (Us.inscription(U)) {
                                    System.out.println(Us.find(la2_textf_user2.getText()));
                                    layer1.setDisable(true);
                                    la1_text2.setText("Consultez l'email envoyé a l'addresse " + la2_textf_email2.getText() + " .  Cet email contient le lien d'activation de votre compte ensuite vous pouriez vous connecter.");

                                }
                                else
                                {
                                    la1_text2.setText("Une erreur est survenue");
                                }


                            }
                            else
                            {
                                la1_text2.setText("L'utilisateur existe déja dans notre base de données");
                            }

                        } else {
                            la1_text2.setText("Le numero de telephone n'est pas valide");
                        }

                    }
                    else
                    {
                        la1_text2.setText("L'addresse existe déja dans notre base de données");
                    }
                }
                else
                {
                    la1_text2.setText("L'addresse E-Mail n'est pas valide");
                }
            }
            else{

                la1_text2.setText("Les mots de passe ne sont pas identiques");

            }

        }


    }

    @FXML
    private void btn_signin_la2(MouseEvent event){
        UserService Us = new UserService();

        if(!la2_textf_user1.getText().equals("") && !la2_textf_pass1.getText().equals("")) {
            try {
                User U = new User(la2_textf_user1.getText(), la2_textf_pass1.getText());
                if (Us.connexion(U)) {

                    Preferences userPreferences = Preferences.userRoot();
                    userPreferences.put("username",la2_textf_user1.getText());

                    Parent root = null;
                    try {
                        root = FXMLLoader.load(getClass().getResource("../../User/View/accueil.fxml"));
                    } catch (IOException ex) {
                        Logger.getLogger(Loading.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();

                    stage.setScene(scene);
                    stage.initStyle(StageStyle.UNDECORATED);
                    stage.show();
                    parent.getScene().getWindow().hide();

                } else {
                    la1_text11.setText("Attention");
                    la1_text1.setText("Le nom d'utilisateur et le mot de passe ne correspondent pas");
                }
            } catch (NumberFormatException var3) {
                la1_text1.setText("Une erreur est survenue");
            } catch (Exception var4) {
                var4.printStackTrace();
            }
        }
        else
        {
            la1_text11.setText("Attention");
            la1_text1.setText("Saisissez votre nom d'utilisateur et votre mot de passe");
        }
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

    private boolean validateNumber(JFXTextField input){
        Pattern p = Pattern.compile("[0-9]{8}");
        Matcher m = p.matcher(input.getText());
        return m.find() && m.group().equals(input.getText());
    }

    private boolean validateEmail(JFXTextField input){
        Pattern p = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+");
        Matcher m = p.matcher(input.getText());
        return m.find() && m.group().equals(input.getText());
    }

}
