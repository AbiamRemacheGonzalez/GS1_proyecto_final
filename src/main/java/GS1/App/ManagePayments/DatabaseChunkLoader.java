package GS1.App.ManagePayments;

import GS1.Model.ChunckPayment;
import GS1.Model.Payment;
import GS1.View.ChunckPaymentLoader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class DatabaseChunkLoader implements ChunckPaymentLoader {

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
    public ArrayList<ChunckPayment> getChunksPayment(int balanceId) {
        ArrayList<ChunckPayment> chunks = new ArrayList<>();
        String sql = "SELECT * FROM chunkpayments WHERE balanceId='" + balanceId + "'";
        try {
            st.execute(sql);
            ResultSet r = st.getResultSet();
            while (r.next()) {
                ChunckPayment chunk = new ChunckPayment(Integer.parseInt(r.getString("paymentId")), Integer.parseInt(r.getString("balanceId")), Double.parseDouble(r.getString("chunkAmount")));
                chunk.setId(Integer.parseInt(r.getString("chunkId")));
                chunks.add(chunk);
            }
        } catch (SQLException ex) {

        }
        return chunks;
    }
    
    @Override
    public Payment getPaymentById(int paymentId){
        Payment payment = null;
        String sql = "SELECT * FROM payments WHERE paymentsID='" + paymentId + "'";
        try {
            st.execute(sql);
            ResultSet r = st.getResultSet();
            while (r.next()) {
                payment = new Payment(r.getString("title"), Double.parseDouble(r.getString("amount")), Integer.parseInt(r.getString("destinationUserID")),Integer.parseInt(r.getString("groupId")));
                payment.setPaymentId(Integer.parseInt(r.getString("paymentsId")));
            }
        } catch (SQLException ex) {

        }
        return payment;
    }

}
