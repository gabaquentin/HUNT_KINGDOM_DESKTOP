package Produits.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class StatistiquesProduits implements Initializable {

    @FXML private BarChart<?,?> Chart;
    @FXML private CategoryAxis x;
    @FXML private NumberAxis y;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String sql ="Select categorie, count(*) from produits group by categorie";
        XYChart.Series series=new XYChart.Series<>();
        try {
            Connection cnx= DataSource.getInstance().getCnx();
            PreparedStatement stat = cnx.prepareStatement(sql);
            ResultSet rs = stat.executeQuery();
            while(rs.next()){
                series.getData().add(new XYChart.Data(rs.getString(1),rs.getInt(2)));
            }
            Chart.getData().addAll(series);


        } catch (Exception e) {
            e.printStackTrace();
        }


    }
    /*
    private void showstat(){
        String sql ="Select categorie, count(*) from produits group by categorie";
        XYChart.Series series=new XYChart.Series<>();
        try{
            Connection cnx= DataSource.getInstance().getCnx();
            PreparedStatement stat = cnx.prepareStatement(sql);
            ResultSet rs = stat.executeQuery();
            while(rs.next()){
                series.getData().add(new XYChart.Data("James",2000));
            }
            Chart.getData().addAll(series);
        }catch(Exception e){}
    }

     */
}
