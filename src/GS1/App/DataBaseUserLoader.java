/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GS1.App;

import GS1.Model.User;
import GS1.View.UserLoader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Exulonk
 */
public class DataBaseUserLoader implements UserLoader{

    private Connection cn;
    private Statement st;
    public DataBaseUserLoader(){
        try {
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gs1?zeroDateTimeBehavior=CONVERT_TO_NULL","root","");
            st =cn.createStatement();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Not Connected");
        }
    }

    @Override
    public User load(String mail, String password) {
        User loadUser = null;
        try {
            String sql = "SELECT * FROM users WHERE email='"+mail+"' and password="+password;
            boolean res = st.execute(sql);
            System.out.println(res);
            st.getResultSet();
            
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseUserLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return loadUser;
    }
    
}
