package GS1.App.SearchFriend;

import GS1.App.UserLoginAndSignUp.DataBaseUserLoader;
import GS1.Model.User;
import GS1.View.UserSearch;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DataBaseUserSearch implements UserSearch {
    private Connection cn;
    private Statement st;
    
    public DataBaseUserSearch() {
        try {
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gs1?zeroDateTimeBehavior=CONVERT_TO_NULL","root","");
            st =cn.createStatement();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Not Connected");
        }
    }
    
    @Override
    public ArrayList<String> search(String search, User currentUser) {
        ArrayList<String> res = new ArrayList<String>();
        try {
        String sql = "SELECT firstname,userId FROM users WHERE email LIKE '"+search+"%'";
        st.execute(sql);
        ResultSet r = st.getResultSet();
        
        while(r.next()){
            if(!currentUser.getFirstname().equals(r.getString("firstname"))){
                res.add(r.getString("firstname")+"#"+r.getString("userId"));
            }
        }
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseUserLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }
    
    @Override
    public void sendFriendRequest(int sourceUserId, int destinationUserId) {
        String sql = "INSERT INTO requests(sourceUserId,destionationUserId,requestType) VALUES('"+sourceUserId+"','"+destinationUserId+"','F');";
        try {
            st.execute(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseUserSearch.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
