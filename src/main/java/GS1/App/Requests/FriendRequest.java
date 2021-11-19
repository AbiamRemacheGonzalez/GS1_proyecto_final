package GS1.App.Requests;

import GS1.App.UserLoginAndSignUp.DataBaseUserLoader;
import GS1.Control.UserAccessControl;
import GS1.Model.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import GS1.View.FriendRequestLoader;

public class FriendRequest implements FriendRequestLoader {

    private Connection cn;
    private Statement st;

    public FriendRequest() {

        try {
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gs1?zeroDateTimeBehavior=CONVERT_TO_NULL", "root", "");
            st = cn.createStatement();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Not Connected");
        }
    }

    @Override
    public ArrayList<String> showFriendResquests() {
        ArrayList<String> res = new ArrayList<String>();
        return res;
    }

    @Override
    public void addFriend(String friend) {
        
    }

    @Override
    public void discardFriend(String user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
