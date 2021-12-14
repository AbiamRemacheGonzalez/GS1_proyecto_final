package GS1.App.SearchUser;

import GS1.App.UserLoginAndSignUp.DataBaseUserLoader;
import GS1.Model.Request;
import GS1.Model.User;
import GS1.View.UserSearch;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DataBaseUserSearch implements UserSearch {
    private Connection cn;
    private Statement st;
    
    public DataBaseUserSearch() {
        try {
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gs1?zeroDateTimeBehavior=CONVERT_TO_NULL","root","");
            st =cn.createStatement();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Not Connected");
        }
    }
    
    @Override
    public ArrayList<User> search(String search, User currentUser) {
        ArrayList<User> res = new ArrayList<>();
        try {
        String sql = "SELECT * FROM users WHERE email LIKE '"+search+"%'";
        st.execute(sql);
        ResultSet r = st.getResultSet();
        while(r.next()){
            User current = new User(r.getString("firstname"),r.getString("lastname"),r.getString("email"),r.getString("password"));
            //!= currentUser    s(YES)
            //!= friends        (NO)
            //!= requested      (NO)
            System.out.println(current.getMail()+" == "+currentUser.getMail());
            if(!currentUser.getMail().equals(current.getMail())){
                res.add(current);
            }
        }
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseUserLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }
    
    @Override
    public boolean sendRequest(Request request) {
        String sql = "INSERT INTO requests(sourceUserId,destinationUserId,requestType) VALUES('"+request.getSourceId()+"','"+request.getUserDestinationId()+"','"+request.getRequestType()+"');";
        if(request.getRequestType()=='G'){
            sql = "INSERT INTO requests(groupId,destinationUserId,requestType) VALUES('"+request.getSourceId()+"','"+request.getUserDestinationId()+"','"+request.getRequestType()+"');";
        }
        try {
            st.execute(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseUserSearch.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
}
