package Urgence.Controllers;

import Expedition.Services.ExpeditionService;
import Urgence.Model.Urgence;
import Urgence.Services.UrgenceService;
import User.Controllers.Loading;
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
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    @FXML
    private JFXCheckBox expCheck;

    @FXML
    private JFXTextField plus;

    @FXML
    private JFXComboBox<String> exp;

    @FXML
    private Pane erreur;

    @FXML
    private Pane succes;

    @FXML
    private Label text_erreur;

    @FXML
    private JFXTextArea description;

    @FXML
    private Pane rigthPanel;

    @FXML
    private Pane rigthSticksPanel;

    @FXML
    private Pane expedition;

    @FXML
    private Pane rigthSticksPanel1;

    @Override
    public void initialize(URL location, ResourceBundle resources){

        btn_menu_exit.setVisible(false);
        btn_menu_exitbars.setVisible(false);
        formUrgenceBody.setOpacity(0);
        formUrgenceBody.setVisible(false);
        formUrgenceBtn1.setVisible(false);
        plus.setVisible(false);
        erreur.setVisible(false);
        succes.setVisible(false);
        rigthPanel.setVisible(false);
        rigthSticksPanel1.setVisible(false);

        ExpeditionService Es = new ExpeditionService();

        int size = Es.afficher().size();
        int count = 0;

        for(int i=0; i< size ; i++ ){

            exp.getItems().addAll(Es.afficher().get(i).getNom());
            count+=1;

        }
        System.out.println(count);
        exp.getSelectionModel().select(Es.afficher().get(0).getNom());
        handleDragged();
        WebEngine engine = mapUrgence.getEngine();
        URL url = this.getClass().getResource("../View/mapUrgence.html");
        engine.load(url.toString());
        //engine.load("http://127.0.0.1:8000/emergency/desk");
    }

    @FXML
    private void expedition(MouseEvent event){
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../../Expedition/View/expeditions.fxml"));
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
    private void add_urgence(MouseEvent event) throws Exception {

        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        System.out.println(dateFormat.format(cal.getTime()));

        if(description.getText().trim().isEmpty()){

            erreur.setVisible(true);
        }

        else{

            if(expCheck.isSelected()){
                if(plus.getText().trim().isEmpty()){
                    erreur.setVisible(true);
                }
                else{


                        erreur.setVisible(false);

                        ExpeditionService Es = new ExpeditionService();
                        Urgence U = new Urgence(Es.find(exp.getValue()),"tarek", get_lat(), get_long(), get_address(), get_placeId(), description.getText(), plus.getText(), dateFormat.format(cal.getTime()), "1");

                        UrgenceService Us = new UrgenceService();

                        Us.ajouter(U);

                    plus.setText("");
                    description.setText("");

                    MouseEvent event1 = null;
                    closeformUrgenceBody(event1);

                    succes.setVisible(true);
                    FadeTransition fade = new FadeTransition();
                    fade.setDuration(Duration.millis(5000));
                    fade.setFromValue(0);
                    fade.setToValue(1);
                    fade.setCycleCount(2);
                    fade.setAutoReverse(true);
                    fade.setNode(succes);
                    fade.play();

                    fade.setOnFinished((e ->{
                        succes.setVisible(false);
                    } ));

                }
            }
            else{

                if(exp.getValue().equals("selectionnez une expedition"))
                {
                    erreur.setVisible(true);
                }
                else {
                    erreur.setVisible(false);

                    ExpeditionService Es = new ExpeditionService();
                    Urgence U = new Urgence(Es.find(exp.getValue()), "tarek", get_lat(), get_long(), get_address(), get_placeId(), description.getText(), plus.getText(), dateFormat.format(cal.getTime()), "0");

                    UrgenceService Us = new UrgenceService();

                    Us.ajouter(U);

                    exp.getSelectionModel().select(Es.afficher().get(0).getNom());
                    description.setText("");

                    MouseEvent event1 = null;

                    closeformUrgenceBody(event1);

                    succes.setVisible(true);
                    FadeTransition fade = new FadeTransition();
                    fade.setDuration(Duration.millis(5000));
                    fade.setFromValue(0);
                    fade.setToValue(1);
                    fade.setCycleCount(2);
                    fade.setAutoReverse(true);
                    fade.setNode(succes);
                    fade.play();

                    fade.setOnFinished((e ->{
                        succes.setVisible(false);
                    } ));
                }
            }

        }

        /*
        System.out.println(Es.find(exp.getValue()));
        System.out.println(exp.getValue());
        Us.afficher().forEach(System.out::println);
        Es.afficher().forEach(System.out::println);

         */

    }

    @FXML
    private void checkExp(MouseEvent event){

        if(expCheck.isSelected()){
            ExpeditionService Es = new ExpeditionService();
            plus.setVisible(true);
            exp.setVisible(false);
            exp.getSelectionModel().select(Es.afficher().get(0).getNom());
        }
        else{
            plus.setVisible(false);
            exp.setVisible(true);
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

        slide.setOnFinished((e ->{
            rigthPanel.setVisible(false);
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

    public static String get_ip() throws Exception {
        String url = "https://ipapi.co/json/";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        // optional default is GET
        con.setRequestMethod("GET");
        //add request header
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to Ip : " + url);
        System.out.println("Response Code : " + responseCode);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        //print in String
        System.out.println(response.toString());
        //Read JSON response and print
        JSONObject myResponse = new JSONObject(response.toString());
        System.out.println("result after Reading JSON Response");
        System.out.println("ip- "+myResponse.getString("ip"));

        return myResponse.getString("ip");
    }

    public static double get_lat() throws Exception {
        String url = "http://ip-api.com/json/"+get_ip();
        URL obj = new URL(url);

        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        // optional default is GET
        con.setRequestMethod("GET");
        //add request header
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to Lat : " + url);
        System.out.println("Response Code : " + responseCode);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        //print in String
        System.out.println(response.toString());
        //Read JSON response and print
        JSONObject myResponse = new JSONObject(response.toString());
        System.out.println("result after Reading JSON Response");
        System.out.println("lat- "+myResponse.getDouble("lat"));

        return myResponse.getDouble("lat");
    }

    public static double get_long() throws Exception {
        String url = "http://ip-api.com/json/"+get_ip();
        URL obj = new URL(url);

        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        // optional default is GET
        con.setRequestMethod("GET");
        //add request header
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to Long : " + url);
        System.out.println("Response Code : " + responseCode);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        //print in String
        System.out.println(response.toString());
        //Read JSON response and print
        JSONObject myResponse = new JSONObject(response.toString());
        System.out.println("result after Reading JSON Response");
        System.out.println("long- "+myResponse.getDouble("lon"));

        return myResponse.getDouble("lon");
    }

    public static String get_placeId() throws Exception {
        String url = "https://maps.googleapis.com/maps/api/geocode/json?latlng="+ get_lat() +","+ get_long() +"&key=AIzaSyD2Ws0KYSjxNXXgRh8jRBGZgrXqgNHzWbI";
        URL obj = new URL(url);

        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        // optional default is GET
        con.setRequestMethod("GET");
        //add request header
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to Long : " + url);
        System.out.println("Response Code : " + responseCode);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        //print in String
        System.out.println(response.toString());
        //Read JSON response and print
        JSONObject myResponse = new JSONObject(response.toString());
        System.out.println("result after Reading JSON Response");
        JSONArray results = myResponse.getJSONArray("results");
        System.out.println("placeId- "+results.getJSONObject(3).getString("place_id"));


        return results.getJSONObject(3).getString("place_id");
    }

    public static String get_address() throws Exception {
        String url = "https://maps.googleapis.com/maps/api/geocode/json?latlng="+ get_lat() +","+ get_long() +"&key=AIzaSyD2Ws0KYSjxNXXgRh8jRBGZgrXqgNHzWbI";
        URL obj = new URL(url);

        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        // optional default is GET
        con.setRequestMethod("GET");
        //add request header
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to Long : " + url);
        System.out.println("Response Code : " + responseCode);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        //print in String
        System.out.println(response.toString());
        //Read JSON response and print
        JSONObject myResponse = new JSONObject(response.toString());
        System.out.println("result after Reading JSON Response");
        JSONObject results = myResponse.getJSONObject("plus_code");
        System.out.println("address- "+results.getString("compound_code"));


        return results.getString("compound_code");
    }
}
