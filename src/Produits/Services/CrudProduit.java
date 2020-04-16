package Produits.Services;

import Produits.Controllers.DataSource;
import Produits.Model.Produits;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CrudProduit {

    public static int ajoutP(Produits p){
        Connection cnx= DataSource.getInstance().getCnx();
        int st=0;
        String req="insert into Produits(nomProduit,Quantite,categorie,prix,image,fournisseur,description) values(?,?,?,?,?,?,?)";
        try {

            PreparedStatement pst=cnx.prepareStatement(req);
            pst.setString(1,p.getNomProduit());
            pst.setInt(2,p.getQuantite());
            pst.setString(3,p.getCategorie());
            pst.setInt(4,p.getPrix());
            pst.setString(5,p.getImage());
            pst.setInt(6,p.getFournisseur());
            pst.setString(7,p.getDescription());
            st=pst.executeUpdate();
            cnx.close();
            pst.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }


        return st;
    }

    public static int modifP(Produits p)
    {
        int st=0;
        String req="UPDATE Produits SET nomProduit=?,Quantite=?,categorie=?,prix=?,image=?,fournisseur=?,description=? where id=?";
        Connection cnx= DataSource.getInstance().getCnx();
        try {

            PreparedStatement pst=cnx.prepareStatement(req);
            pst.setInt(1,p.getId());
            pst.setString(2,p.getNomProduit());
            pst.setInt(3,p.getQuantite());
            pst.setString(4,p.getCategorie());
            pst.setInt(5,p.getPrix());
            pst.setString(6,p.getImage());
            pst.setInt(7,p.getFournisseur());
            pst.setString(8,p.getDescription());
            st=pst.executeUpdate();
            //cnx.close();


        } catch (SQLException e) {
            e.printStackTrace();
        }


        return st;
    }

    public void updatetab(Produits a) throws SQLException {
        try {
            Connection cnx= DataSource.getInstance().getCnx();
            PreparedStatement PS=cnx.prepareStatement("UPDATE `produits` SET `nomProduit`=? ,`description`=? WHERE `id`=?");
            PS.setString(1,a.getNomProduit());
            PS.setString(2, a.getDescription());
            PS.setInt(3,a.getId());
            PS.executeUpdate();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Succès");
            alert.setHeaderText("Succès");
            alert.setContentText("Produit Modifié Avec Succès!");
            alert.showAndWait();
        } catch (Exception e) {
            Logger.getLogger(CrudProduit.class.getName()).log(Level.SEVERE,null,e);
        }

    }

    public static int supprP(int id){
        int st=0;
        try {
            String req="DELETE FROM Produits WHERE id=?";
            Connection cnx= DataSource.getInstance().getCnx();
            PreparedStatement pst=cnx.prepareStatement(req);
            pst.setInt(1,id);
            st=pst.executeUpdate();
            cnx.close();



        } catch (SQLException e) {
            e.printStackTrace();
        }
        return st;
    }

    public static Produits chercherP(int id){
        Produits p = new Produits();
        try {
            String req="SELECT * FROM Produits where id=?";
            Connection cnx= DataSource.getInstance().getCnx();
            PreparedStatement pst=cnx.prepareStatement(req);
            pst.setInt(1,id);
            ResultSet rst=pst.executeQuery();
            if (rst.next()) {
                p.setId(rst.getInt(1));
                p.setNomProduit(rst.getString(2));
                p.setQuantite(rst.getInt(3));
                p.setCategorie(rst.getString(4));
                p.setPrix(rst.getInt(5));
                p.setImage(rst.getString(6));
                p.setFournisseur(rst.getInt(7));
                p.setDescription(rst.getString(8));

            }



        } catch (Exception e) {
            e.printStackTrace();
        }
        return p;

    }
}
