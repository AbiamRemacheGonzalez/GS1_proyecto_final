package GS1.App.UserLoginAndSignUp;

import GS1.Model.CreditCardPaymentMethod;
import GS1.Model.PaymentMethod;
import GS1.View.PaymentMethodLogger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    public void save(int UserId, PaymentMethod paymentMethod) {
        CreditCardPaymentMethod credit = (CreditCardPaymentMethod)paymentMethod;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String sql = "INSERT INTO creditcards(owner,creditNumber,expiryDate,userId) VALUES('"+credit.getOwner()+"','"+credit.getCreditNumber().toString()+"','"+format.format(credit.getExpiryDate())+"','"+UserId+"');";
        try {
            boolean res = st.execute(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseUserLogger.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
