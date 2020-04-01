package Expedition;

import Expedition.Model.Expedition;
import Expedition.Services.ExpeditionService;
import Urgence.Model.Urgence;
import Urgence.Services.UrgenceService;
import dataSource.DataSource;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Expeditions extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../Expedition/View/expeditions.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();
    }

    public static void main(String[] args) {

        DataSource ds=DataSource.getInstance();

        System.out.println(ds.hashCode());

        //***********************Ajout************************//
        Expedition E=new Expedition("venomia","0","23/01/2015","23/01/2016","23/01/2014","On va tuer","amazonie","chasse");
        ExpeditionService Es=new ExpeditionService();
        Es.ajouter(E);

        //**********************Suppression**********************//

        // Es.supprimer(U);

        //**********************Affichage***********************//
        Es.afficher().forEach(System.out::println);
    }
}
