package GS1.App.CreateGroup;

import GS1.Model.Group;
import GS1.View.GroupLoader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class DataBaseGroupLoader implements GroupLoader {

    private Connection cn;
    private Statement st;

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
                newgroup.setIdGroup(groupId);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseGroupLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return newgroup;
    }
    
}
