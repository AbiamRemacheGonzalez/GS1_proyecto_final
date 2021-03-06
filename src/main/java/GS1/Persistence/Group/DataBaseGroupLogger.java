package GS1.Persistence.Group;

import GS1.Persistence.User.DataBaseUserLogger;
import GS1.Model.Group;
import GS1.View.GroupLogger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class DataBaseGroupLogger implements GroupLogger {

    private Connection cn;
    private Statement st;

    public DataBaseGroupLogger() {
        try {
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gs1?zeroDateTimeBehavior=CONVERT_TO_NULL", "root", "");
            st = cn.createStatement();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Not Connected");
        }
    }

    @Override
    public void save(int UserId, Group group) {
        String sql = "INSERT INTO groups(userId,name,description) VALUES('" + UserId + "','" + group.getName() + "','" + group.getDescription() + "');";
        try {
            boolean res = st.execute(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseUserLogger.class.getName()).log(Level.SEVERE, null, ex);
        }

        String sql1 = "SELECT groupId FROM groups WHERE userId='" + UserId + "' and name='" + group.getName() + "' and description='" + group.getDescription() + "'";
        try {
            st.execute(sql1);
            ResultSet r = st.getResultSet();
            while (r.next()) {
                String sql2 = "INSERT INTO members(groupId,userId) VALUES('" + r.getString("groupId") + "','" + UserId + "');";
                try {
                    st.execute(sql2);
                } catch (SQLException ex) {
                    Logger.getLogger(DataBaseUserLogger.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(ex.getMessage());
        }
    }

    public int getGroupId(int UserId, String name, String description) {
        int res = -1;
        try {
            String sql = "SELECT groupId FROM groups WHERE userId='" + UserId + "' and name='" + name + "' and description='" + description + "'";
            st.execute(sql);
            ResultSet r = st.getResultSet();
            while (r.next()) {
                res = Integer.parseInt(r.getString("groupId"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseGroupLogger.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }

    public ArrayList<Group> getGroups(int UserId) {
        ArrayList<Integer> groupIds = getIdGroups(UserId);
        ArrayList<Group> groupsIBelongIn = new ArrayList();

        for (Integer id : groupIds) {
            try {
                String sql = "SELECT * FROM groups WHERE groupId='" + id + "'";
                st.execute(sql);
                ResultSet r = st.getResultSet();

                while (r.next()) {
                    Group newG = new Group(r.getString("name"), r.getString("description"));
                    newG.setIdGroup(id);
                    groupsIBelongIn.add(newG);
                    break;
                }

            } catch (SQLException ex) {
                Logger.getLogger(DataBaseGroupLogger.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return groupsIBelongIn;
    }

    private ArrayList<Integer> getIdGroups(int UserId) {
        ArrayList<Integer> groupIds = new ArrayList();
        try {
            String sql = "SELECT groupId FROM members WHERE userId='" + UserId + "'";
            st.execute(sql);
            ResultSet r = st.getResultSet();
            while (r.next()) {
                groupIds.add(Integer.parseInt(r.getString("groupId")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseGroupLogger.class.getName()).log(Level.SEVERE, null, ex);
        }
        return groupIds;
    }
    
    public boolean isAdminOfCurrentGroup(int userId, int groupId){
        String sql = "SELECT userId FROM groups WHERE groupId='"+groupId+"'";
        int adminId = 0;
        try {
            st.execute(sql);
            ResultSet r = st.getResultSet();
            while (r.next()) {
                adminId = Integer.parseInt(r.getString("userId"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseGroupLogger.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        if(adminId == userId)return true;
        return false;
    }
}
