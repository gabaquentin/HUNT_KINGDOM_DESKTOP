package dataSource;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {
    private static DataSource instance;
    private final String URL="jdbc:mysql://localhost:3306/hunt";
    private final String LOGIN="root";
    private final String MDP="";

    private Connection cnx;

    private DataSource(){
        try {
            cnx= DriverManager.getConnection(URL,LOGIN,MDP);
            System.out.println("Connecting...");
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public static DataSource getInstance(){
        if (instance==null)
            instance = new DataSource();
        return instance;



    }

    public Connection getCnx() {
        return cnx;
    }
}
