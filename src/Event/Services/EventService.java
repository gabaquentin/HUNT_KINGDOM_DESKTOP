package Event.Services;

import Event.Model.Event;
import Event.Services.IService;
import dataSource.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventService implements IService<Event> {

    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Event E) {
        String req = "insert into event(nom, description, dateDebut, dateFin) values('" + E.getNom() + "','" + E.getDescription() + "','" + E.getDateDebut() + "','" + E.getDateFin() + "','" + "')";
        try {
            Statement st = cnx.createStatement();
            st.executeUpdate(req);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    @Override
    public int find(String nom) {
        int id = 0;
        String req = "SELECT * FROM event WHERE nom = '"+nom+"'";
        try {
            PreparedStatement pst = cnx.prepareStatement(req); //preparer cnx avec BD
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
    public String findName(int id) {
        String nom = "";
        String req = "SELECT * FROM event WHERE id = '"+id+"'";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                nom = rs.getString(2);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return nom;
    }

    @Override
    public void modifier(Event E) {

    }

    @Override
    public void supprimer(Event E) {
        String req = "delete from event where id=?";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, E.getId());
            pst.executeUpdate();
            System.out.println("Evenement Supprimé avec succès!");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Event> afficher() {
        List<Event> list = new ArrayList<>();
        String req = "SELECT * FROM event";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Event E = new Event(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
                list.add(E);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }
}
