package GS1.Control;


import GS1.App.Requests.FriendRequest;
import GS1.App.Requests.FriendRequestDisplay;
import GS1.App.UserLoginAndSignUp.DataBaseUserLoader;
import GS1.Model.User;
import GS1.View.UserSearch;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class FriendRequestControl {
    private Connection cn;
    private Statement st;
    
    private FriendRequestDisplay friendRequestDisplay;
    private FriendRequest friendRequest = new FriendRequest();

    public FriendRequestControl(FriendRequestDisplay friendRequestDisplay) {
        this.friendRequestDisplay = friendRequestDisplay;
        this.friendRequestDisplay.on(setFriendRequestDisplay());
        
        try {
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gs1?zeroDateTimeBehavior=CONVERT_TO_NULL","root","");
            st =cn.createStatement();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Not Connected");
        }
    }
    
    private FriendRequestDisplay.Events setFriendRequestDisplay(){
        return new FriendRequestDisplay.Events(){

            @Override
            public ArrayList<String> showFriendResquests() {
                return friendRequest.showFriendResquests();
            }

            @Override
            public void addFriend(String friend) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void discardFriend(String user) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
            
                   
        };
    }
}
