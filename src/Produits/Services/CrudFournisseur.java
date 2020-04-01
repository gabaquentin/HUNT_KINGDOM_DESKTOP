package Produits.Services;

import Produits.Controllers.DataSource;
import Produits.Model.Fournisseurs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
        try {
            String req="DELETE FROM fournisseur WHERE id=?";
            Connection cnx= DataSource.getInstance().getCnx();
            PreparedStatement pst=cnx.prepareStatement(req);
            pst.setInt(1,id);
            st=pst.executeUpdate();
            cnx.close();

        } catch (Exception e) {
            e.printStackTrace();
        }


        return st;

    }











}
