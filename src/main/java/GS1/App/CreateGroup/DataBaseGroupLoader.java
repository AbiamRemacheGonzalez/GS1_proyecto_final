package GS1.App.CreateGroup;

import GS1.Model.Group;
import GS1.View.GroupLoader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class DataBaseGroupLoader implements GroupLoader {

    private Connection cn;
    private Statement st;

    public DataBaseGroupLoader() {
        try {
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gs1?zeroDateTimeBehavior=CONVERT_TO_NULL","root","");
            st =cn.createStatement();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Not Connected");
        }
    }
    
    
    @Override
    public Group load(int userId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
