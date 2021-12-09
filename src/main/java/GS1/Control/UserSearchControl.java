package GS1.Control;

import GS1.App.UserLoginAndSignUp.DataBaseUserLoader;
import GS1.Model.User;
import GS1.App.SearchUser.SearchUsersDisplay;
import GS1.App.SearchUser.DataBaseUserSearch;
import GS1.App.UserMainDisplay;
import GS1.Model.Request;

import java.util.ArrayList;

public class UserSearchControl {
    private final UserMainDisplay userMainDisplay;
    private SearchUsersDisplay searchUsersDisplay;
    
    private DataBaseUserSearch databaseUserSearch = new DataBaseUserSearch();
    private final DataBaseUserLoader dataBaseUserLoader = new DataBaseUserLoader();
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
            public ArrayList<User> search(String search) {
                return databaseUserSearch.search(search,currentUser);
            }

            @Override
            public void SendFriendRequest(String friend) {
                int sourceUserId = currentUser.getId();//userLoader.loadUserId(currentUser.getMail(), currentUser.getPassword());
                int destinationUserId = Integer.parseInt(friend.substring(friend.indexOf("#")+1));
                Request request = new Request(sourceUserId,destinationUserId,'F');
                databaseUserSearch.sendRequest(request);
            }

            @Override
            public int getUserId(User user) {
                return currentUser.getId();//dataBaseUserLoader.loadUserId(user.getMail(), user.getPassword());
            }
        };
    }
    
}
