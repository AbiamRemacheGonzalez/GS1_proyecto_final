package GS1.App.Group;

import GS1.App.UserLoginAndSignUp.DataBaseUserLoader;
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
//CLASE PENDIENTE DE REVISAR
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
    @Override
    public ArrayList<String> search(String search, User currentUser) {
        ArrayList<String> friends = new ArrayList<String>();
        try {
            int userId = userLoader.loadUserId(currentUser.getMail(), currentUser.getPassword());
            ArrayList<Integer> friendIds = getFriendsId(userId);
            for (Integer friendId : friendIds){ 
                String sql="";
                if (search.isEmpty()){
                    sql = "SELECT firstname,userId FROM users WHERE userId = friendId";
                }else{
                    sql = "SELECT firstname,userId FROM users WHERE userId = friendId and email LIKE '"+search+"%'";
                }
                st.execute(sql);
                ResultSet r = st.getResultSet();
                while(r.next()){
                    if(!currentUser.getFirstname().equals(r.getString("firstname"))){
                        friends.add(r.getString("firstname")+"#"+r.getString("userId"));
                    }
                }
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseUserLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return friends;
    }
    
    private ArrayList<Integer> getFriendsId(int userId) {
        ArrayList<Integer> friendsId = new ArrayList<>();
        try {
            String sql = "SELECT idUser2 FROM friends WHERE idUser1 = '"+userId+"'";
            st.execute(sql);
            ResultSet r = st.getResultSet();
            while(r.next()){
                friendsId.add(Integer.parseInt(r.getString("idUser2")));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseFriendsLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return friendsId;
    }

    @Override
    public void sendFriendRequest(int sourceUserId, int destinationUserId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
