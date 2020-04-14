package Urgence.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class UrgenceAdminItineraire {

    public javafx.scene.control.Label title1;
    public Label title2;
    public Label title3;
    @FXML
    private WebView itineraireUrgence;

    public void drawRoute(String ADDR, String USER, String DATE, String ETAT){
        WebEngine engine = itineraireUrgence.getEngine();
        engine.load("http://127.0.0.1:8000/adminEmergency/position/"+ADDR);

        title1.setText("Urgence de L'utilisateur "+USER);
        title2.setText("Survenue le "+DATE);
        title3.setText("Etat : "+ETAT);
    }

}
