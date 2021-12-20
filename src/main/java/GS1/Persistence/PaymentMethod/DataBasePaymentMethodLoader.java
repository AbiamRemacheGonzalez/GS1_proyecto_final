/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GS1.Persistence.PaymentMethod;

import GS1.Model.PaymentsMethods.BizumPaymentMethod;
import GS1.Model.PaymentsMethods.CreditCardPaymentMethod;
import GS1.Model.PaymentsMethods.PaymentMethod;
import GS1.Model.PaymentsMethods.PaypalPaymentMethod;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class DataBasePaymentMethodLoader {
    private Connection cn;
    private Statement st;
    
    public DataBasePaymentMethodLoader() {
        try {
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gs1?zeroDateTimeBehavior=CONVERT_TO_NULL","root","");
            st =cn.createStatement();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Not Connected");
        }
    }
    
    public CreditCardPaymentMethod loadCreditCardUser(int userId){
        CreditCardPaymentMethod creditCard = null;
        String sql = "SELECT * FROM creditcards WHERE userId='"+userId+"'";
        try {
            st.execute(sql);
            ResultSet r = st.getResultSet();
            while(r.next()){
                creditCard = new CreditCardPaymentMethod(r.getString("owner"),r.getLong("creditNumber"),r.getDate("expiryDate"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataBasePaymentMethodLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return creditCard;
    }
    
    public ArrayList<PaymentMethod> getPaymentMethods(int userId){
        ArrayList<PaymentMethod> paymentMethods = new ArrayList<>();
        String[] sqls = {"SELECT * FROM bizumaccounts WHERE userId ='"+userId+"'",
            "SELECT * FROM creditcards WHERE userId ='"+userId+"'",
            "SELECT * FROM paypalaccounts WHERE userId ='"+userId+"'"};
        try {
            st.execute(sqls[0]);
            ResultSet r = st.getResultSet();
            while(r.next()){
                BizumPaymentMethod bizum = new BizumPaymentMethod(Integer.parseInt(r.getString("telephoneNumber")));
                paymentMethods.add(bizum);
            }
 
            st.execute(sqls[1]);
            r = st.getResultSet();
            while(r.next()){
                CreditCardPaymentMethod creditCard = new CreditCardPaymentMethod(r.getString("owner"),r.getLong("creditNumber"),r.getDate("expiryDate"));
                paymentMethods.add(creditCard);
            }

            st.execute(sqls[2]);
            r = st.getResultSet();
            while(r.next()){
                PaypalPaymentMethod paypal = new PaypalPaymentMethod(r.getString("paypalemail"));
                paymentMethods.add(paypal);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataBasePaymentMethodLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return paymentMethods;
        
    }
}
