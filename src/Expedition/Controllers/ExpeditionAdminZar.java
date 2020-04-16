package Expedition.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.ResourceBundle;

public class ExpeditionAdminZar implements Initializable {

    @FXML
    private WebView Zar ;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        WebEngine engine = Zar.getEngine();
        URL url = this.getClass().getResource("../View/mapExpedition.html");
        //engine.load(url.toString());
        engine.load("http://127.0.0.1:8000/emergency/deskExpedition");

    }
}
