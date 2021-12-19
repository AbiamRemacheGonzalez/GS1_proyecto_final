/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GS1.Persistence.PaymentMethod;

import GS1.Model.PaymentsMethods.CreditCardPaymentMethod;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
}
