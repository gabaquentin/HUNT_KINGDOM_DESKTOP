package Urgence;

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

public class Urgency extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../Urgence/View/urgences.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();
    }

    public static void main(String[] args) {

        DataSource ds=DataSource.getInstance();

        System.out.println(ds.hashCode());

        //***********************Ajout************************//

        ExpeditionService Es=new ExpeditionService();
        Expedition E = Es.find("venomia");

        Urgence U=new Urgence(E,"venomia","36.7278251","10.70978669999999","Yaounde","CHU404","jai peur","23/01/2015","1");
        UrgenceService Us=new UrgenceService();
        Us.ajouterExp(U);

        //**********************Suppression**********************//

       // Us.supprimer(U);

        //**********************Affichage***********************//
        Us.afficher().forEach(System.out::println);
    }
}
