package GS1.Control;

import GS1.App.UserLoginAndSignUp.DataBaseUserLoader;
import GS1.Model.User;
import GS1.View.UserSearch;
import GS1.App.SearchFriend.SearchUserDisplay;
import GS1.App.SearchFriend.SearchUserSeeker;

import java.sql.Statement;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import javax.swing.JOptionPane;


public class SearchUserControl {
    
    private Connection cn;
    private Statement st;
    
    private SearchUserDisplay searchUserDisplay;
    private UserSearch userSearch = new SearchUserSeeker();

    public SearchUserControl(SearchUserDisplay searchUserDisplay) {
        this.searchUserDisplay = searchUserDisplay;
        this.searchUserDisplay.on(setFriendSearchDisplay());
        
        try {
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gs1?zeroDateTimeBehavior=CONVERT_TO_NULL","root","");
            st =cn.createStatement();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Not Connected");
        }
    }
    
    private SearchUserDisplay.Events setFriendSearchDisplay(){
        return new SearchUserDisplay.Events(){
           
            @Override
            public ArrayList<String> search(String search, User currentUser) {
                return userSearch.search(search,currentUser);
            }
            
            @Override
            public void addFriend(String friend) {
                DataBaseUserLoader userLoader = new DataBaseUserLoader();
                int idUser1 = userLoader.loadUserId(UserAccessControl.loggedUser.getMail(), UserAccessControl.loggedUser.getPassword());
                int idUser2 = Integer.parseInt(friend.substring(friend.indexOf("#")+1));
                boolean confirmed = false;
                String sql = "INSERT INTO friends(idUser1,firstname1,idUser2,confirmed) VALUES('"+idUser1+"','"+UserAccessControl.loggedUser.getFirstname()+"','"+idUser2+"',FALSE);";
                try {
                    st.execute(sql);
                    
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Not Connected 2");
                }
                
            }
            
            /*
            @Override
            public void openNewFriendRequestsWindow(){
                System.out.println("Hola");
            } 
            */
           
        };
    }
    
    
}
