package GS1.Control;

import GS1.App.UserLoginAndSignUp.DataBaseUserLoader;
import GS1.Model.User;
import GS1.View.UserSearch;
import GS1.App.SearchFriend.SearchUsersDisplay;
import GS1.App.SearchFriend.DataBaseUserSearch;
import GS1.App.UserMainDisplay;

import java.sql.Statement;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import javax.swing.JOptionPane;


public class UserSearchControl {
    private final UserMainDisplay userMainDisplay;
    private SearchUsersDisplay searchUsersDisplay;
    
    private DataBaseUserSearch databaseUserSearch = new DataBaseUserSearch();
    DataBaseUserLoader userLoader = new DataBaseUserLoader();
    
    private final User currentUser;
    
    UserSearchControl(UserMainDisplay userMainDisplay, User loggedUser) {
        this.currentUser = loggedUser;
        this.userMainDisplay = userMainDisplay;
        userMainDisplay.on(setUserMainDisplaySearchEvents());
    }
    
    private UserMainDisplay.SearchEvents setUserMainDisplaySearchEvents() {
        return new UserMainDisplay.SearchEvents(){
            @Override
            public void openUserSearchWindow() {
                searchUsersDisplay = new SearchUsersDisplay();
                searchUsersDisplay.on(setSearchUsersDisplayEvents());
                searchUsersDisplay.setVisible(true);
            }
        };
    }
    private SearchUsersDisplay.Events setSearchUsersDisplayEvents() {
        return new SearchUsersDisplay.Events() {
            @Override
            public ArrayList<String> search(String search) {
                return databaseUserSearch.search(search,currentUser);
            }

            @Override
            public void SendFriendRequest(String friend) {
                int sourceUserId = userLoader.loadUserId(UserAccessControl.loggedUser.getMail(), UserAccessControl.loggedUser.getPassword());
                int destinationUserId = Integer.parseInt(friend.substring(friend.indexOf("#")+1));
                databaseUserSearch.sendFriendRequest(sourceUserId,destinationUserId);
            }
        };
    }
    
}
