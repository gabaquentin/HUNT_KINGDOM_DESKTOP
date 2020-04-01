package Produits.Services;

import Produits.Controllers.DataSource;
import Produits.Model.Produits;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class CrudProduit {

    public static int ajout(Produits p){
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
}
