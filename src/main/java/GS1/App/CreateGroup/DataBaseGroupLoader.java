package GS1.App.CreateGroup;

import GS1.App.ManagePayments.DatabasePaymentLogger;
import GS1.App.UserLoginAndSignUp.DataBaseUserLoader;
import GS1.Model.Group;
import GS1.Model.User;
import GS1.View.GroupLoader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class DataBaseGroupLoader implements GroupLoader {

    private Connection cn;
    private Statement st;
    private final DataBaseUserLoader databaseUserLoader = new DataBaseUserLoader();

    public DataBaseGroupLoader() {
        try {
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gs1?zeroDateTimeBehavior=CONVERT_TO_NULL","root","");
            st =cn.createStatement();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Not Connected");
        }
    }
    
    
    @Override
    public Group load(int groupId) {
        Group newgroup = null;
        String sql = "SELECT * FROM groups WHERE groupId='"+groupId+"'";
        try {
            st.execute(sql);
            ResultSet r = st.getResultSet();
            while(r.next()){
                newgroup = new Group(r.getString("name"),r.getString("description"));
                newgroup.setIdAdmin(Integer.parseInt(r.getString("userId")));
                newgroup.setIdGroup(groupId);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseGroupLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return newgroup;
    }
    
    public ArrayList<User> getGroupMembers(int groupID){
        ArrayList<User> res = new ArrayList<>();
        String sql = "SELECT userId FROM members WHERE groupId='"+groupID+"'";
        try {
            st.execute(sql);
            ResultSet r = st.getResultSet();
            while(r.next()){
                int userId = Integer.parseInt(r.getString("userId"));
                User user = databaseUserLoader.loadUser(userId);
                user.setId(userId);
                res.add(user);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabasePaymentLogger.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }
}
