package GS1.Persistence.Payment;

import GS1.Model.ChunkPayment;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import GS1.View.ChunkPaymentLogger;

public class DatabaseChunkLogger implements ChunkPaymentLogger {

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
    public boolean save(ChunkPayment chunckPayment) {
        String sql = "INSERT INTO chunkpayments (balanceId,paymentId,chunkAmount) VALUES('"+chunckPayment.getBalanceId()+"','"+chunckPayment.getPaymentId()+"','"+chunckPayment.getChunckAmount()+"')";
        try {
            st.execute(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DatabasePaymentLogger.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
    
    public void updateChunckPayment(ChunkPayment chunckPayment){
        String sql = "UPDATE chunkpayments SET chunkAmount='"+chunckPayment.getChunckAmount()+"' WHERE chunkId='"+chunckPayment.getChunckPaymentid()+"'";
        try {
            st.execute(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DatabasePaymentLogger.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
