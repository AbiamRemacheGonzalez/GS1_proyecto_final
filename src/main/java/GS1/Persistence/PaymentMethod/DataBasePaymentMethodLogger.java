package GS1.Persistence.PaymentMethod;

import GS1.Model.PaymentsMethods.BizumPaymentMethod;
import GS1.Model.PaymentsMethods.CreditCardPaymentMethod;
import GS1.Model.PaymentsMethods.PaymentMethod;
import GS1.Model.PaymentsMethods.PaypalPaymentMethod;
import GS1.View.PaymentMethodLogger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;

public class DataBasePaymentMethodLogger implements PaymentMethodLogger{
    private Connection cn;
    private Statement st;
    
    public DataBasePaymentMethodLogger() {
        try {
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gs1?zeroDateTimeBehavior=CONVERT_TO_NULL","root","");
            st =cn.createStatement();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Not Connected");
        }
    }
    
    @Override
    public boolean save(int UserId, PaymentMethod paymentMethod) {
        if(paymentMethod instanceof CreditCardPaymentMethod){
            CreditCardPaymentMethod credit = (CreditCardPaymentMethod)paymentMethod;
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String sql = "INSERT INTO creditcards(owner,creditNumber,expiryDate,userId) VALUES('"+credit.getOwner()+"','"+credit.getCreditNumber().toString()+"','"+format.format(credit.getExpiryDate())+"','"+UserId+"');";
            try {
                st.execute(sql);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Database Error: CreditCard not saved");
                return false;
            }
        }
        if(paymentMethod instanceof PaypalPaymentMethod){
            PaypalPaymentMethod paypal = (PaypalPaymentMethod)paymentMethod;
            String sql = "INSERT INTO paypalaccounts(userId,paypalemail) VALUES('"+UserId+"','"+paypal.getEmail()+"');";
            try {
                st.execute(sql);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Database Error: Paypal account not saved");
                return false;
            }
        }
        if(paymentMethod instanceof BizumPaymentMethod){
            BizumPaymentMethod bizum = (BizumPaymentMethod)paymentMethod;
            String sql = "INSERT INTO bizumaccounts(userId,telephoneNumber) VALUES('"+UserId+"','"+bizum.getPhoneNumber()+"');";
            try {
                st.execute(sql);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Database Error: Bizum account not saved");
                return false;
            }
        }
        return true;
    }
    
}
