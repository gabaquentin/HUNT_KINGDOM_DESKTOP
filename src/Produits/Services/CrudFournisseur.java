package Produits.Services;

import Produits.Controllers.DataSource;
import Produits.Model.Fournisseurs;
import Produits.Model.Produits;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CrudFournisseur {

    public static int ajoutF(Fournisseurs f) {
        Connection cnx= DataSource.getInstance().getCnx();
        int st=0;
        String req="insert into fournisseur (nomFournisseur,idFournisseur,emailFournisseur) values (?,?,?)";
        try {
            PreparedStatement pst=cnx.prepareStatement(req);
            pst.setString(1,f.getNomFournisseur());
            pst.setInt(2,f.getIdFournisseur());
            pst.setString(3,f.getEmailFournisseur());
            st=pst.executeUpdate();
            cnx.close();
            pst.executeUpdate();


        } catch (Exception e) {
            e.printStackTrace();
        }


        return st;
    }

    public void updatetab(Fournisseurs a) throws SQLException {
        try {
            Connection cnx= DataSource.getInstance().getCnx();
            PreparedStatement PS=cnx.prepareStatement("UPDATE `fournisseur` SET `nomFournisseur`=? ,`emailFournisseur`=? WHERE `id`=?");
            PS.setString(1,a.getNomFournisseur());
            PS.setString(2, a.getEmailFournisseur());
            PS.setInt(3,a.getId());
            PS.executeUpdate();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Succès");
            alert.setHeaderText("Succès");
            alert.setContentText("Fournisseur Modifié Avec Succès!");
            alert.showAndWait();
        } catch (Exception e) {
            Logger.getLogger(CrudProduit.class.getName()).log(Level.SEVERE,null,e);
        }

    }


    public static Fournisseurs chercherF(int id){
        Fournisseurs f=new Fournisseurs();
        try {
            String req="SELECT * FROM fournisseur where id=?";
            Connection cnx= DataSource.getInstance().getCnx();
            PreparedStatement pst=cnx.prepareStatement(req);
            pst.setInt(1,id);
            ResultSet rst=pst.executeQuery();
            if (rst.next()) {
                f.setId(rst.getInt(1));
                f.setNomFournisseur(rst.getString(2));
                f.setIdFournisseur(rst.getInt(3));
                f.setEmailFournisseur(rst.getString(4));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return f;
    }


    public static int supprF(int id){
        int st=0;
        Connection cnx= DataSource.getInstance().getCnx();
        try {
            String req="DELETE FROM fournisseur WHERE id=?";

            PreparedStatement pst=cnx.prepareStatement(req);
            pst.setInt(1,id);
            st=pst.executeUpdate();
            pst.executeUpdate();
            //cnx.close();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }


        return st;

    }
    public static int modifF(Fournisseurs f){
        int st=0;
        Connection cnx= DataSource.getInstance().getCnx();
        String sql="UPDATE fournisseur SET nomFournisseur=?,idFournisseur=?,emailFournisseur=? where id=? ";
        try {

            PreparedStatement pst=cnx.prepareStatement(sql);
            pst.setInt(1,f.getId());
            pst.setString(2,f.getNomFournisseur());
            pst.setInt(3,f.getIdFournisseur());
            pst.setString(4,f.getEmailFournisseur());
            st= pst.executeUpdate();


        } catch (Exception e) {
            e.printStackTrace();
        }


        return st;
    }

    public List<Fournisseurs> afficher(){
        List<Fournisseurs> list= new ArrayList<>();
        Connection cnx= DataSource.getInstance().getCnx();
        String req="SELECT * FROM fournisseur";
        try {
            PreparedStatement pst=cnx.prepareStatement(req);
            ResultSet rs=pst.executeQuery();
            while (rs.next()){
                Fournisseurs f=(new Fournisseurs(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getString(4)));
                list.add(f);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }
    public int find(String nom)
    {
        int id=0;
        Connection cnx= DataSource.getInstance().getCnx();
        String sql="SELECT * FROM fournisseur where nomFournisseur='"+nom+"'";
        try {
            PreparedStatement pst=cnx.prepareStatement(sql);
            ResultSet res=pst.executeQuery();
            if(res.next()){
                id =res.getInt(1);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;

    }













}
