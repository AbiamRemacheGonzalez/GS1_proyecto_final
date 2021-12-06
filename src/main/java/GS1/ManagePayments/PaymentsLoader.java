
package GS1.ManagePayments;

import GS1.App.UserLoginAndSignUp.DataBaseUserLoader;
import GS1.Model.Payment;
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

/**
 *
 * @author hugob
 */
public class PaymentsLoader {
    private Connection cn;
    private Statement st;
    
    public PaymentsLoader() {
         try {
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gs1?zeroDateTimeBehavior=CONVERT_TO_NULL","root","");
            st =cn.createStatement();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Not Connected");
        }
    }
    
    public void addPayment(Payment p){
        String sql = "INSERT INTO payments(title,amount,destinationUserID,groupId) VALUES('"+p.getTitle()+"','"+p.getAmount()+"','"+p.getUserDestinationID()+"','"+p.getGroupID()+"')";
        try {
            st.execute(sql);
        } catch (SQLException ex) {
            Logger.getLogger(PaymentsLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    public ArrayList<Integer> getMembersId(int groupID){
        ArrayList<Integer> res = new ArrayList<>();
        String sql = "SELECT userId FROM members WHERE groupId='"+groupID+"'";
        try {
            st.execute(sql);
            ResultSet r = st.getResultSet();
            
            while(r.next()){
                res.add(Integer.parseInt(r.getString("userId")));
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(PaymentsLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }
    
    public String getUserByID(int id){
        String res = "";
        String sql = "SELECT firstname FROM users WHERE userId='"+id+"'";
        try {
            st.execute(sql);
            ResultSet r = st.getResultSet();
            while(r.next()){
                res=r.getString("firstname");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PaymentsLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }
    
    public User getUserByName(String firstname){
        User resUser = null;
        String sql = "SELECT * FROM users WHERE firstname='"+firstname+"'";
        try {
            st.execute(sql);
            ResultSet r = st.getResultSet();
            while(r.next()){
                resUser = new User(r.getString("firstname"),r.getString("lastname"),r.getString("email"),r.getString("password"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseUserLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return resUser;

    }
    
    
    
}
