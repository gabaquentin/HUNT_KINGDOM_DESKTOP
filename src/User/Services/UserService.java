package User.Services;

import User.Model.User;
import dataSource.DataSource;

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
}
