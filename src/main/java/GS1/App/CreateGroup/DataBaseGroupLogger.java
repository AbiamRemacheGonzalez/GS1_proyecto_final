package GS1.App.CreateGroup;

import GS1.App.UserLoginAndSignUp.DataBaseUserLogger;
import GS1.Model.Group;
import GS1.View.GroupLogger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class DataBaseGroupLogger implements GroupLogger {

    private Connection cn;
    private Statement st;

    public DataBaseGroupLogger() {
        try {
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gs1?zeroDateTimeBehavior=CONVERT_TO_NULL","root","");
            st =cn.createStatement();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Not Connected");
        }
    }
    
    @Override
    public void save(int UserId, Group group) {
        String sql = "INSERT INTO groups(userId,name,description) VALUES('"+UserId+"','"+group.getName()+"','"+group.getDescription()+"');";
        try {
            boolean res = st.execute(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseUserLogger.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public int getGroupId(int UserId, String name, String description){
        int res = -1;
        try {
            String sql = "SELECT groupId FROM groups WHERE userId='"+UserId+"' and name='"+name+"' and description='"+description+"'";
            st.execute(sql);
            ResultSet r = st.getResultSet();
            while(r.next()){
                res = Integer.parseInt(r.getString("groupId"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseGroupLogger.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }
}
