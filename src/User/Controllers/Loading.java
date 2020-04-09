package User.Controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Loading  implements Initializable {

    @FXML
    private StackPane rootPane ;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        new loading().start();
    }

    class loading extends Thread {
        @Override
        public void run(){
            try {
                Thread.sleep(5000);
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
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
                        rootPane.getScene().getWindow().hide();
                    }
                });

            }catch (InterruptedException ex){
                Logger.getLogger(Loading.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
