package GS1.App.ManagePayments;

import GS1.Model.Balance;
import GS1.View.BalanceLoader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class DatabaseBalanceLoader implements BalanceLoader {

    private Connection cn;
    private Statement st;

    //MAYUS ALT F
    public DatabaseBalanceLoader() {
        try {
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gs1?zeroDateTimeBehavior=CONVERT_TO_NULL", "root", "");
            st = cn.createStatement();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Not Connected");
        }
    }

    @Override
    public Balance getUserBalance(int groupId, int userId) {
        Balance balance = null;
        try {
            String sql = "SELECT * FROM balances WHERE groupId='" + groupId + "' and memberId='" + userId + "'";
            st.execute(sql);
            ResultSet r = st.getResultSet();
            while (r.next()) {
                balance = new Balance(Integer.parseInt(r.getString("memberId")), Integer.parseInt(r.getString("groupId")));
                balance.setBalanceId(Integer.parseInt(r.getString("balanceId")));
                balance.setBalance(Double.parseDouble(r.getString("balanceAmount")));
            }
        }catch (SQLException ex) {
            Logger.getLogger(DatabaseBalanceLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return balance;
    }
    
}
