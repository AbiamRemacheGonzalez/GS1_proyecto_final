package GS1.Persistence.Payment;

import GS1.Model.ChunkPayment;
import GS1.View.ChunkPaymentEraser;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class DatabaseChunkEraser implements ChunkPaymentEraser{
    private Connection cn;
    private Statement st;

    public DatabaseChunkEraser() {
        try {
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gs1?zeroDateTimeBehavior=CONVERT_TO_NULL", "root", "");
            st = cn.createStatement();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Not Connected");
        }
    }

    @Override
    public boolean removeChunk(ChunkPayment chunkPayment) {
        String sql = "DELETE FROM chunkpayments WHERE chunkId='"+chunkPayment.getChunckPaymentid()+"'";
        try {
            st.execute(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DatabasePaymentLogger.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
    
}
