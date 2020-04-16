package Expedition.Controllers;

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
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import java.util.function.Predicate;

public class ExpeditionAdminTable implements Initializable {

    @FXML
    public JFXTreeTableView expeditionList;

    @FXML
    private JFXTextField filterField1;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        JFXTreeTableColumn<ExpeditionsAdmin.Expeditionr, String> idColumn = new JFXTreeTableColumn<ExpeditionsAdmin.Expeditionr, String>("Id");
        JFXTreeTableColumn<ExpeditionsAdmin.Expeditionr, String> nomColumn = new JFXTreeTableColumn<ExpeditionsAdmin.Expeditionr, String>("Nom");
        JFXTreeTableColumn<ExpeditionsAdmin.Expeditionr, String> statutColumn = new JFXTreeTableColumn<ExpeditionsAdmin.Expeditionr, String>("Statut");
        JFXTreeTableColumn<ExpeditionsAdmin.Expeditionr, String> dateDebutColumn = new JFXTreeTableColumn<ExpeditionsAdmin.Expeditionr, String>("Debut");
        JFXTreeTableColumn<ExpeditionsAdmin.Expeditionr, String> dateFinColumn = new JFXTreeTableColumn<ExpeditionsAdmin.Expeditionr, String>("Fin");
        JFXTreeTableColumn<ExpeditionsAdmin.Expeditionr, String> dateColumn = new JFXTreeTableColumn<ExpeditionsAdmin.Expeditionr, String>("Date");
        JFXTreeTableColumn<ExpeditionsAdmin.Expeditionr, String> messageColumn = new JFXTreeTableColumn<ExpeditionsAdmin.Expeditionr, String>("Message");
        JFXTreeTableColumn<ExpeditionsAdmin.Expeditionr, String> lieuxColumn = new JFXTreeTableColumn<ExpeditionsAdmin.Expeditionr, String>("Lieux");
        JFXTreeTableColumn<ExpeditionsAdmin.Expeditionr, String> typeColumn = new JFXTreeTableColumn<ExpeditionsAdmin.Expeditionr, String>("Type");
        JFXTreeTableColumn<ExpeditionsAdmin.Expeditionr, String> userColumn = new JFXTreeTableColumn<ExpeditionsAdmin.Expeditionr, String>("Utilisateur");
        JFXTreeTableColumn<ExpeditionsAdmin.Expeditionr, String> periodeColumn = new JFXTreeTableColumn<ExpeditionsAdmin.Expeditionr, String>("Periode");

        periodeColumn.getColumns().addAll(dateDebutColumn, dateFinColumn);

        idColumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<ExpeditionsAdmin.Expeditionr, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<ExpeditionsAdmin.Expeditionr, String> param) {
                return param.getValue().getValue().id;
            }
        });
        statutColumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<ExpeditionsAdmin.Expeditionr, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<ExpeditionsAdmin.Expeditionr, String> param) {
                return param.getValue().getValue().statut;
            }
        });
        nomColumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<ExpeditionsAdmin.Expeditionr, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<ExpeditionsAdmin.Expeditionr, String> param) {
                return param.getValue().getValue().nom;
            }
        });
        dateDebutColumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<ExpeditionsAdmin.Expeditionr, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<ExpeditionsAdmin.Expeditionr, String> param) {
                return param.getValue().getValue().dateDebut;
            }
        });
        dateFinColumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<ExpeditionsAdmin.Expeditionr, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<ExpeditionsAdmin.Expeditionr, String> param) {
                return param.getValue().getValue().dateFin;
            }
        });
        dateColumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<ExpeditionsAdmin.Expeditionr, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<ExpeditionsAdmin.Expeditionr, String> param) {
                return param.getValue().getValue().date;
            }
        });
        messageColumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<ExpeditionsAdmin.Expeditionr, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<ExpeditionsAdmin.Expeditionr, String> param) {
                return param.getValue().getValue().message;
            }
        });
        lieuxColumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<ExpeditionsAdmin.Expeditionr, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<ExpeditionsAdmin.Expeditionr, String> param) {
                return param.getValue().getValue().lieux;
            }
        });
        typeColumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<ExpeditionsAdmin.Expeditionr, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<ExpeditionsAdmin.Expeditionr, String> param) {
                return param.getValue().getValue().type;
            }
        });
        userColumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<ExpeditionsAdmin.Expeditionr, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<ExpeditionsAdmin.Expeditionr, String> param) {
                return param.getValue().getValue().utilisateur;
            }
        });

        ObservableList<ExpeditionsAdmin.Expeditionr> list = getExpeditionList();

        final TreeItem<ExpeditionsAdmin.Expeditionr> root = new RecursiveTreeItem<ExpeditionsAdmin.Expeditionr>(list, RecursiveTreeObject::getChildren);
        expeditionList.getColumns().setAll(userColumn, periodeColumn, statutColumn, lieuxColumn, messageColumn,typeColumn);
        expeditionList.setRoot(root);
        expeditionList.setShowRoot(false);
        expeditionList.setEditable(true);

        filterField1.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                expeditionList.setPredicate(new Predicate<TreeItem<ExpeditionsAdmin.Expeditionr>>() {
                    @Override
                    public boolean test(TreeItem<ExpeditionsAdmin.Expeditionr> urgencerTreeItem) {
                        Boolean flag = urgencerTreeItem.getValue().id.getValue().toLowerCase().contains(newValue) || urgencerTreeItem.getValue().utilisateur.getValue().toLowerCase().contains(newValue) || urgencerTreeItem.getValue().statut.getValue().toLowerCase().contains(newValue) || urgencerTreeItem.getValue().date.getValue().toLowerCase().contains(newValue) || urgencerTreeItem.getValue().dateDebut.getValue().toLowerCase().contains(newValue);
                        return flag;
                    }
                });
            }
        });

    }

    static class  Expeditionr extends RecursiveTreeObject<ExpeditionsAdmin.Expeditionr> {

        StringProperty id;
        StringProperty nom;
        StringProperty statut;
        StringProperty dateDebut;
        StringProperty dateFin;
        StringProperty date;
        StringProperty message;
        StringProperty lieux;
        StringProperty type;
        StringProperty utilisateur;

        public  Expeditionr (String id, String nom, String statut, String dateDebut, String dateFin, String date, String message, String lieux, String type, String utilisateur){
            this.id = new SimpleStringProperty(id);
            this.nom = new SimpleStringProperty(nom);
            this.utilisateur = new SimpleStringProperty(utilisateur);
            this.statut = new SimpleStringProperty(statut);
            this.dateDebut = new SimpleStringProperty(dateDebut);
            this.dateFin = new SimpleStringProperty(dateFin);
            this.message = new SimpleStringProperty(message);
            this.date = new SimpleStringProperty(date);
            this.lieux = new SimpleStringProperty(lieux);
            this.type = new SimpleStringProperty(type);
        }

    }


    Connection cnx= DataSource.getInstance().getCnx();
    public List<ExpeditionsAdmin.Expeditionr> afficherExp() {
        List<ExpeditionsAdmin.Expeditionr> list = new ArrayList<>();
        String req = "SELECT * FROM expedition";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {

                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                DateFormat dateFormat2 = new SimpleDateFormat("HH:mm");
                Calendar cal = Calendar.getInstance();

                Date dateNow = null;
                try {
                    dateNow = dateFormat.parse(dateFormat.format(cal.getTime())+" "+dateFormat2.format(cal.getTime()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Date dateDebut = null;
                try {
                    dateDebut = dateFormat.parse(rs.getString(4).substring(0, 10)+" "+rs.getString(4).substring(11));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                Date dateFin = null;
                try {
                    dateFin = dateFormat.parse(rs.getString(5).substring(0, 10)+" "+rs.getString(5).substring(11));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                Timestamp tsdd = new Timestamp(dateDebut.getTime());
                Timestamp tsdf = new Timestamp(dateFin.getTime());
                Timestamp tsdn = new Timestamp(dateNow.getTime());
                Double Progress = (double)(100 - (((tsdf.getTime() - (tsdn.getTime() + 3600)) * 100)/ (tsdf.getTime() - tsdd.getTime())));

                if((tsdd.getTime() - (tsdn.getTime()+3600)) > 0){

                    ExpeditionsAdmin.Expeditionr E = new ExpeditionsAdmin.Expeditionr(rs.getString(1),rs.getString(2), "en attente", rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10));
                    list.add(E);

                }
                else if((tsdd.getTime() - (tsdn.getTime() + 3600)) < 0){
                    if((tsdf.getTime() - (tsdn.getTime() + 3600)) > 0){
                        ExpeditionsAdmin.Expeditionr E = new ExpeditionsAdmin.Expeditionr(rs.getString(1),rs.getString(2), Progress+"% Terminé", rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10));
                        list.add(E);
                    }
                    else if((tsdf.getTime() - (tsdn.getTime() + 3600)) < 0){
                        ExpeditionsAdmin.Expeditionr E = new ExpeditionsAdmin.Expeditionr(rs.getString(1),rs.getString(2), "Terminé", rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10));
                        list.add(E);
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    private ObservableList<ExpeditionsAdmin.Expeditionr> getExpeditionList() {
        ObservableList<ExpeditionsAdmin.Expeditionr> list = FXCollections.observableArrayList(afficherExp());
        return list;
    }

}


