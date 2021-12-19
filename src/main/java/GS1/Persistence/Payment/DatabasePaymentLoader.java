package GS1.Persistence.Payment;

import GS1.Model.ChunkPayment;
import GS1.Model.Payment;
import GS1.View.PaymentLoader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class DatabasePaymentLoader implements PaymentLoader{
    private Connection cn;
    private Statement st;
    private final DatabaseChunkLoader chunkLoader = new DatabaseChunkLoader();
    private final DatabasePaymentEraser paymentEraser = new DatabasePaymentEraser();
    
    public DatabasePaymentLoader() {
        try {
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gs1?zeroDateTimeBehavior=CONVERT_TO_NULL","root","");
            st =cn.createStatement();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Not Connected");
        }
    }
    
    @Override
    public ArrayList<Payment> getGroupsPayments(int groupId) {
        ArrayList<Payment> payments = new ArrayList<>();
        String sql = "SELECT * FROM payments WHERE groupId='" + groupId + "'";
        try {
            st.execute(sql);
            ResultSet r = st.getResultSet();
            while (r.next()) {
                Payment payment = new Payment(r.getString("title"), Double.parseDouble(r.getString("amount")), Integer.parseInt(r.getString("destinationUserID")),Integer.parseInt(r.getString("groupId")));
                payment.setPaymentId(Integer.parseInt(r.getString("paymentsId")));
                payments.add(payment);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabasePaymentLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return payments;
    }

    
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
    
    public int getPaymentId(Payment p) {
        int paymentId = -1;
        try {
            String sql = "SELECT paymentsId FROM payments WHERE title='"+p.getTitle()+"' and amount='"+p.getAmount()+"' and destinationUserID ='"+p.getUserDestinationID()+"' and groupId='"+p.getGroupID()+"'";
            st.execute(sql);
            ResultSet r = st.getResultSet();
            while(r.next()){
                paymentId = Integer.parseInt(r.getString("paymentsId"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabasePaymentLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return paymentId;
    }
    
    //De forma automatica reconoce si existen chunks asociados, en caso de no haber chunks asociados elimina el payment
    public void updateGroupPayments(int groupId){
        ArrayList<Payment> payments = getGroupsPayments(groupId);
        for (Payment payment : payments) {
            ArrayList<ChunkPayment> chunks = chunkLoader.getChunksPaymentByPaymentId(payment.getPaymentId());
            if(chunks.isEmpty()) paymentEraser.removePayment(payment);
        }
    }
    
}
