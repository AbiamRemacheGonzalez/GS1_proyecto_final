package GS1.Persistence.Payment;

import GS1.Model.ChunkPayment;
import GS1.Model.Payment;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import GS1.View.ChunkPaymentLoader;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseChunkLoader implements ChunkPaymentLoader {

    private Connection cn;
    private Statement st;

    public DatabaseChunkLoader() {
        try {
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gs1?zeroDateTimeBehavior=CONVERT_TO_NULL", "root", "");
            st = cn.createStatement();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Not Connected");
        }
    }

    @Override
    public ArrayList<ChunkPayment> getChunksPayment(int balanceId) {
        ArrayList<ChunkPayment> chunks = new ArrayList<>();
        String sql = "SELECT * FROM chunkpayments WHERE balanceId='" + balanceId + "'";
        try {
            st.execute(sql);
            ResultSet r = st.getResultSet();
            while (r.next()) {
                ChunkPayment chunk = new ChunkPayment(Integer.parseInt(r.getString("paymentId")), Integer.parseInt(r.getString("balanceId")), Double.parseDouble(r.getString("chunkAmount")));
                chunk.setId(Integer.parseInt(r.getString("chunkId")));
                chunks.add(chunk);
            }
        } catch (SQLException ex) {

        }
        return chunks;
    }
    
    public ChunkPayment getChunkPayment(int chunkId){
        String sql = "SELECT * FROM chunkpayments WHERE chunkId='" + chunkId + "'";
        ChunkPayment chunk = null;
        try {
            st.execute(sql);
            ResultSet r = st.getResultSet();
            while (r.next()) {
                chunk = new ChunkPayment(Integer.parseInt(r.getString("paymentId")), Integer.parseInt(r.getString("balanceId")), Double.parseDouble(r.getString("chunkAmount")));
                chunk.setId(chunkId);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseChunkLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return chunk;
    }
    
    public ArrayList<ChunkPayment> getChunksPaymentByPaymentId(int paymentId) {
        ArrayList<ChunkPayment> chunks = new ArrayList<>();
        String sql = "SELECT * FROM chunkpayments WHERE paymentId='" + paymentId + "'";
        try {
            st.execute(sql);
            ResultSet r = st.getResultSet();
            while (r.next()) {
                ChunkPayment chunk = new ChunkPayment(Integer.parseInt(r.getString("paymentId")), Integer.parseInt(r.getString("balanceId")), Double.parseDouble(r.getString("chunkAmount")));
                chunk.setId(Integer.parseInt(r.getString("chunkId")));
                chunks.add(chunk);
            }
        } catch (SQLException ex) {

        }
        return chunks;
    }
    
    public ArrayList<ChunkPayment> getUserChunksPaymentsWithAEspecificDestination(int groupId, int balanceId, int destinationUserId){
        ArrayList<ChunkPayment> chunks = new ArrayList<>();
        String sql = "SELECT * FROM payments JOIN chunkpayments WHERE balanceId='"+balanceId+"' AND destinationUserID='"+destinationUserId+"' AND groupId='"+groupId+"' AND payments.paymentsID=chunkpayments.paymentId";
        try {
            st.execute(sql);
            ResultSet r = st.getResultSet();
            while (r.next()) {
                ChunkPayment chunk = new ChunkPayment(Integer.parseInt(r.getString("paymentId")), Integer.parseInt(r.getString("balanceId")), Double.parseDouble(r.getString("chunkAmount")));
                chunk.setId(Integer.parseInt(r.getString("chunkId")));
                chunks.add(chunk);
            }
        } catch (SQLException ex) {

        }
        return chunks;
    }
}
