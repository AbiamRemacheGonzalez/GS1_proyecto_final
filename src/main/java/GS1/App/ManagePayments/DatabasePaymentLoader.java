package GS1.App.ManagePayments;

import GS1.Model.Payment;
import GS1.View.PaymentLoader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class DatabasePaymentLoader implements PaymentLoader{
    private Connection cn;
    private Statement st;
    
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
