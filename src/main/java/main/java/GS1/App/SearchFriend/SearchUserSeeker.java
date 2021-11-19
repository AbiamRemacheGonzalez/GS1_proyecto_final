/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GS1.App.SearchFriend;

import GS1.App.UserLoginAndSignUp.DataBaseUserLoader;
import GS1.View.UserSearch;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.sql.ResultSet;

/**
 *
 * @author hugob
 */
public class SearchUserSeeker implements UserSearch{
    
    private Connection cn;
    private Statement st;
    
    public SearchUserSeeker() {
        
        try {
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gs1?zeroDateTimeBehavior=CONVERT_TO_NULL","root","");
            st =cn.createStatement();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Not Connected");
        }
    }
    
    

    @Override
    public String[] search(String search) {
        String[] res = new String[50];
        try {
        String sql = "SELECT firstname,userId FROM users WHERE email LIKE '"+search+"%'";
        st.execute(sql);
        ResultSet r = st.getResultSet();
        int i = 0;
        while(r.next() && i < res.length){
            res[i] = r.getString("firstname")+"#"+r.getString("userId");
            i++;
        }
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseUserLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }
    
    @Override
    public void addFriend(String friend){
        
        
        
    }
    
}
