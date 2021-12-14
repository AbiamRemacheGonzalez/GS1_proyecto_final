package GS1.App.ManagePayments;

import GS1.Model.ChunckPayment;
import GS1.View.ChunckPaymentLogger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class DatabaseChunkLogger implements ChunckPaymentLogger {

    private Connection cn;
    private Statement st;

    public DatabaseChunkLogger() {
        try {
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gs1?zeroDateTimeBehavior=CONVERT_TO_NULL", "root", "");
            st = cn.createStatement();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Not Connected");
        }
    }

    @Override
    public boolean save(ChunckPayment chunckPayment) {
        String sql = "INSERT INTO chunks (idPayment,idBalance,chunkAmount) VALUES('"+chunckPayment.getPaymentId()+"','"+chunckPayment.getBalanceId()+"','"+chunckPayment.getChunckAmount()+"')";
        try {
            st.execute(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DatabasePaymentLogger.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

}
