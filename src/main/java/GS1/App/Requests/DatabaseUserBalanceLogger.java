package GS1.App.Requests;

import GS1.App.ManagePayments.DatabasePaymentLogger;
import GS1.Model.Balance;
import GS1.View.UserBalanceLogger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class DatabaseUserBalanceLogger implements UserBalanceLogger{
    private Connection cn;
    private Statement st;
    
    public DatabaseUserBalanceLogger() {
        try {
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gs1?zeroDateTimeBehavior=CONVERT_TO_NULL","root","");
            st =cn.createStatement();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Not Connected");
        }
    }
    
    @Override
    public boolean save(Balance balance) {
        String sql = "INSERT INTO balances(memberId,groupId,balanceAmount) VALUES('"+balance.getMemberId()+"','"+balance.getGroupId()+"','"+balance.getBalance()+"')";
        try {
            st.execute(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DatabasePaymentLogger.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
    
    public void updateBalance(Balance balance){
        String sql = "UPDATE balances SET balanceAmount='"+balance.getBalance()+"' WHERE balanceId='"+balance.getBalanceId()+"'";
        try {
            st.execute(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DatabasePaymentLogger.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
