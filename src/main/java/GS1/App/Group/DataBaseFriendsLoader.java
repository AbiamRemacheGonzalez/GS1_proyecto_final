package GS1.App.Group;

import GS1.App.UserLoginAndSignUp.DataBaseUserLoader;
import GS1.Model.Request;
import GS1.Model.User;
import GS1.View.UserSearch;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class DataBaseFriendsLoader implements UserSearch{
    private Connection cn;
    private Statement st;
    private DataBaseUserLoader userLoader = new DataBaseUserLoader();
    
    public DataBaseFriendsLoader() {
        try {
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gs1?zeroDateTimeBehavior=CONVERT_TO_NULL","root","");
            st =cn.createStatement();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Not Connected");
        }
    }
    
    private ArrayList<Integer> getFriendsId(int userId) {
        ArrayList<Integer> friendsId = new ArrayList<>();
        try {
            String sql = "SELECT destinationUserId FROM friends WHERE sourceUserId = '"+userId+"'";
            st.execute(sql);
            ResultSet r = st.getResultSet();
            while(r.next()){
                friendsId.add(Integer.parseInt(r.getString("destinationUserId")));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseFriendsLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return friendsId;
    }

    @Override
    public ArrayList<User> search(String search, User currentUser) {
        ArrayList<User> friends = new ArrayList<>();
        try {
            //int userId = userLoader.loadUserId(currentUser.getMail(), currentUser.getPassword());
            ArrayList<Integer> friendIds = getFriendsId(currentUser.getId());
            for (Integer friendId : friendIds){ 
                String sql="";
                if (search.isEmpty()){
                    sql = "SELECT * FROM users WHERE userId = '"+friendId+"'";
                }else{
                    sql = "SELECT * FROM users WHERE userId = '"+friendId+"' and email LIKE '"+search+"%'";
                }
                st.execute(sql);
                ResultSet r = st.getResultSet();
                while(r.next()){
                    User loadUser = new User(r.getString("firstname"),r.getString("lastname"),r.getString("email"),r.getString("password"));
                    int idUser = Integer.parseInt(r.getString("userId"));
                    loadUser.setId(idUser);
                    friends.add(loadUser);
                }
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseUserLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return friends;
    }
    @Override
    public boolean sendRequest(Request request) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
