package User.Services;

import User.Model.User;
import dataSource.DataSource;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserService implements IService<User> {

    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public int find(String username) {
        int id = 0;
        String req = "SELECT * FROM fos_user WHERE username = '"+username+"'";
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

    public void connexion(User U) throws Exception {
        String url = "http://127.0.0.1:8000/register/M/"+U.getEmail()+"/"+U.getUsername()+"/"+U.getPassword()+"/"+U.getNom()+"/"+U.getPrenom()+"/"+U.getTelephone()+"/"+U.getNum_auth()+"/"+U.getPenalite()+"/"+U.getAdresse()+"/"+U.getRoles();
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        // optional default is GET
        con.setRequestMethod("GET");
        //add request header
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to Ip : " + url);
        System.out.println("Response Code : " + responseCode);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
    }
}
