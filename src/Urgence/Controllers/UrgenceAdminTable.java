package Urgence.Controllers;

import Expedition.Services.ExpeditionService;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import dataSource.DataSource;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.util.Callback;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class UrgenceAdminTable implements Initializable {

    @FXML
    public JFXTreeTableView urgenceList;

    @FXML
    private JFXTextField filterField1;


    @Override
    public void initialize(URL location, ResourceBundle resources) {


        JFXTreeTableColumn<UrgencesAdmin.Urgencer, String> IdColumn = new JFXTreeTableColumn<UrgencesAdmin.Urgencer, String>("Id");
        JFXTreeTableColumn<UrgencesAdmin.Urgencer, String> UserColumn = new JFXTreeTableColumn<UrgencesAdmin.Urgencer, String>("Utilisateur");
        JFXTreeTableColumn<UrgencesAdmin.Urgencer, String> AddressColumn = new JFXTreeTableColumn<UrgencesAdmin.Urgencer, String>("Adresse");
        JFXTreeTableColumn<UrgencesAdmin.Urgencer, String> AddressNameColumn = new JFXTreeTableColumn<UrgencesAdmin.Urgencer, String>("Nom");
        JFXTreeTableColumn<UrgencesAdmin.Urgencer, String> LatColumn = new JFXTreeTableColumn<UrgencesAdmin.Urgencer, String>("Latitude");
        JFXTreeTableColumn<UrgencesAdmin.Urgencer, String> LongColumn = new JFXTreeTableColumn<UrgencesAdmin.Urgencer, String>("Longitude");
        JFXTreeTableColumn<UrgencesAdmin.Urgencer, String> DescColumn = new JFXTreeTableColumn<UrgencesAdmin.Urgencer, String>("Description");
        JFXTreeTableColumn<UrgencesAdmin.Urgencer, String> PlusColumn = new JFXTreeTableColumn<UrgencesAdmin.Urgencer, String>("Plus");
        JFXTreeTableColumn<UrgencesAdmin.Urgencer, String> ExpColumn = new JFXTreeTableColumn<UrgencesAdmin.Urgencer, String>("Expedition");
        JFXTreeTableColumn<UrgencesAdmin.Urgencer, String> IdpColumn = new JFXTreeTableColumn<UrgencesAdmin.Urgencer, String>("Id");
        JFXTreeTableColumn<UrgencesAdmin.Urgencer, String> DateColumn = new JFXTreeTableColumn<UrgencesAdmin.Urgencer, String>("Date");
        JFXTreeTableColumn<UrgencesAdmin.Urgencer, String> EtatColumn = new JFXTreeTableColumn<UrgencesAdmin.Urgencer, String>("Etat");
        JFXTreeTableColumn<UrgencesAdmin.Urgencer, String> ActionColumn = new JFXTreeTableColumn<UrgencesAdmin.Urgencer, String>("Action");

        AddressColumn.getColumns().addAll(AddressNameColumn, IdpColumn);

        IdColumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<UrgencesAdmin.Urgencer, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<UrgencesAdmin.Urgencer, String> param) {
                return param.getValue().getValue().id;
            }
        });
        UserColumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<UrgencesAdmin.Urgencer, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<UrgencesAdmin.Urgencer, String> param) {
                return param.getValue().getValue().Utilisateur;
            }
        });
        AddressNameColumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<UrgencesAdmin.Urgencer, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<UrgencesAdmin.Urgencer, String> param) {
                return param.getValue().getValue().addresse;
            }
        });
        DescColumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<UrgencesAdmin.Urgencer, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<UrgencesAdmin.Urgencer, String> param) {
                return param.getValue().getValue().description;
            }
        });
        PlusColumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<UrgencesAdmin.Urgencer, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<UrgencesAdmin.Urgencer, String> param) {
                return param.getValue().getValue().plus;
            }
        });
        ExpColumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<UrgencesAdmin.Urgencer, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<UrgencesAdmin.Urgencer, String> param) {
                return param.getValue().getValue().expediton;
            }
        });
        IdpColumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<UrgencesAdmin.Urgencer, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<UrgencesAdmin.Urgencer, String> param) {
                return param.getValue().getValue().place_id;
            }
        });
        DateColumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<UrgencesAdmin.Urgencer, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<UrgencesAdmin.Urgencer, String> param) {
                return param.getValue().getValue().date;
            }
        });
        EtatColumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<UrgencesAdmin.Urgencer, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<UrgencesAdmin.Urgencer, String> param) {
                return param.getValue().getValue().etat;
            }
        });
        ActionColumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<UrgencesAdmin.Urgencer, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<UrgencesAdmin.Urgencer, String> param) {
                return param.getValue().getValue().Utilisateur;
            }
        });

        ObservableList<UrgencesAdmin.Urgencer> list = getUrgenceList();

        final TreeItem<UrgencesAdmin.Urgencer> root = new RecursiveTreeItem<UrgencesAdmin.Urgencer>(list, RecursiveTreeObject::getChildren);
        urgenceList.getColumns().setAll(UserColumn, AddressColumn, DescColumn,PlusColumn,DateColumn, EtatColumn);
        urgenceList.setRoot(root);
        urgenceList.setShowRoot(false);
        urgenceList.setEditable(true);


        filterField1.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                urgenceList.setPredicate(new Predicate<TreeItem<UrgencesAdmin.Urgencer>>() {
                    @Override
                    public boolean test(TreeItem<UrgencesAdmin.Urgencer> urgencerTreeItem) {
                        Boolean flag = urgencerTreeItem.getValue().id.getValue().contains(newValue) || urgencerTreeItem.getValue().Utilisateur.getValue().contains(newValue) || urgencerTreeItem.getValue().etat.getValue().contains(newValue) || urgencerTreeItem.getValue().date.getValue().contains(newValue) || urgencerTreeItem.getValue().addresse.getValue().contains(newValue);
                        return flag;
                    }
                });
            }
        });

    }

    static class  Urgencer extends RecursiveTreeObject<UrgencesAdmin.Urgencer> {

        StringProperty id;
        StringProperty expediton;
        StringProperty Utilisateur;
        StringProperty addresse;
        StringProperty description;
        StringProperty plus;
        StringProperty place_id;
        StringProperty date;
        StringProperty etat;

        public Urgencer (String id, String expedition, String Utilisateur, String addresse, String description, String plus, String place_id, String date, String etat){
            this.id = new SimpleStringProperty(id);
            this.expediton = new SimpleStringProperty(expedition);
            this.Utilisateur = new SimpleStringProperty(Utilisateur);
            this.addresse = new SimpleStringProperty(addresse);
            this.description = new SimpleStringProperty(description);
            this.plus = new SimpleStringProperty(plus);
            this.place_id = new SimpleStringProperty(place_id);
            this.date = new SimpleStringProperty(date);
            this.etat = new SimpleStringProperty(etat);
        }

    }


    private ObservableList<UrgencesAdmin.Urgencer> getUrgenceList() {
        ObservableList<UrgencesAdmin.Urgencer> list = FXCollections.observableArrayList(afficher2());
        return list;
    }

    Connection cnx= DataSource.getInstance().getCnx();

    public List<UrgencesAdmin.Urgencer> afficher2() {
        List<UrgencesAdmin.Urgencer> list= new ArrayList<>();
        String req="SELECT * FROM urgence ORDER BY etat";
        try {
            PreparedStatement pst=cnx.prepareStatement(req);
            ResultSet rs=pst.executeQuery();
            while (rs.next()){
                ExpeditionService Es = new ExpeditionService();
                if(rs.getInt(2) == 1)
                {
                    if(rs.getString(11).equals("0"))
                    {
                        UrgencesAdmin.Urgencer U=new UrgencesAdmin.Urgencer(rs.getString(1), "",rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(9),rs.getString(10),"A traiter");
                        list.add(U);
                    }
                    else if(rs.getString(11).equals("1"))
                    {
                        UrgencesAdmin.Urgencer U=new UrgencesAdmin.Urgencer(rs.getString(1), "",rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(9),rs.getString(10),"Déja traité");
                        list.add(U);
                    }
                }
                else
                {
                    if(rs.getString(11).equals("0"))
                    {
                        UrgencesAdmin.Urgencer U=new UrgencesAdmin.Urgencer(rs.getString(1), Es.findName(rs.getInt(2)),rs.getString(3),rs.getString(4),rs.getString(5),"",rs.getString(9),rs.getString(10),"A traiter");
                        list.add(U);
                    }
                    else if(rs.getString(11).equals("1"))
                    {
                        UrgencesAdmin.Urgencer U=new UrgencesAdmin.Urgencer(rs.getString(1), Es.findName(rs.getInt(2)),rs.getString(3),rs.getString(4),rs.getString(5),"",rs.getString(9),rs.getString(10),"Déja traité");
                        list.add(U);
                    }

                }


            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

}


