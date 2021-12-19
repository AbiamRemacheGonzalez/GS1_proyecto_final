
package GS1.Persistence.Payment;

import GS1.Model.Payment;
import GS1.View.PaymentLogger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class DatabasePaymentLogger implements PaymentLogger{
    private Connection cn;
    private Statement st;
    
    public DatabasePaymentLogger() {
        try {
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gs1?zeroDateTimeBehavior=CONVERT_TO_NULL","root","");
            st =cn.createStatement();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Not Connected");
        }
    }
    @Override
    public boolean save(Payment p) {
        String sql = "INSERT INTO payments(title,amount,destinationUserID,groupId) VALUES('"+p.getTitle()+"','"+p.getAmount()+"','"+p.getUserDestinationID()+"','"+p.getGroupID()+"')";
        try {
            st.execute(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DatabasePaymentLogger.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
}
