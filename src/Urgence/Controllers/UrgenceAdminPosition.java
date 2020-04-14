package Urgence.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class UrgenceAdminPosition {

    public javafx.scene.control.Label title1;
    public Label title2;
    public Label title3;
    @FXML
    private WebView positionUrgence;

    public void setMap(String PID, String USER, String DATE, String ETAT){
        WebEngine engine = positionUrgence.getEngine();
        engine.load("http://127.0.0.1:8000/adminEmergency/itineraire/"+PID);

        title1.setText("Urgence de L'utilisateur "+USER);
        title2.setText("Survenue le "+DATE);
        title3.setText("Etat : "+ETAT);
    }

}
