package Expedition.Services;

import Expedition.Model.Expedition;
import dataSource.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExpeditionService implements IService<Expedition> {

    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Expedition E) {
        String req = "insert into expedition(nom, statut, dateDebut, dateFin, date, message, lieux, type) values('" + E.getNom() + "','" + E.getStatut() + "','" + E.getDateDebut() + "','" + E.getDateFin() + "','" + E.getDate() + "','" + E.getMessage() + "','" + E.getLieux() + "','" + E.getType() + "')";
        try {
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    @Override
    public int find(String nom) {
        int id = 0;
        String req = "SELECT * FROM expedition WHERE nom = '"+nom+"'";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                id = rs.getInt(1);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return id;
    }

    @Override
    public void modifier(Expedition E) {

    }

    @Override
    public void supprimer(Expedition E) {
        String req = "delete from expedition where id=?";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, E.getId());
            pst.executeUpdate();
            System.out.println("Expedition Supprimée avec succès!");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Expedition> afficher() {
        List<Expedition> list = new ArrayList<>();
        String req = "SELECT * FROM expedition";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Expedition E = new Expedition(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getInt(10));
                list.add(E);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }
}
