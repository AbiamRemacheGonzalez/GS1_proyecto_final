package GS1.App;

import GS1.Model.User;
import GS1.View.UserLogger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class DataBaseUserLogger implements UserLogger{

    private Connection cn;
    private Statement st;
    public DataBaseUserLogger(){
        try {
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gs1?zeroDateTimeBehavior=CONVERT_TO_NULL","root","");
            st =cn.createStatement();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Not Connected");
        }
    }
    @Override
    public void save(User user) {
        String sql = "INSERT INTO users(firstname,lastname,email,password) VALUES('"+user.getFirstname()+"','"+user.getLastname()+"','"+user.getMail()+"','"+user.getPassword()+"');";
        try {
            boolean res = st.execute(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseUserLogger.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
