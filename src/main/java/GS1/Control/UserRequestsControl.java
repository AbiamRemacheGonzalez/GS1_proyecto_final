package GS1.Control;

import GS1.Persistence.Group.DataBaseGroupLoader;
import GS1.Persistence.Request.DataBaseRequestLoader;
import GS1.Persistence.Payment.DatabaseBalanceLogger;
import GS1.App.Requests.UserFriendRequestsDisplay;
import GS1.App.Requests.UserGroupRequestsDisplay;
import GS1.Persistence.User.DataBaseUserLoader;
import GS1.App.UserMainDisplay;
import GS1.Model.Balance;
import GS1.Model.Group;
import GS1.Model.Request;
import GS1.Model.User;
import java.util.ArrayList;

public class UserRequestsControl {
    private final UserMainDisplay userMainDisplay;
    private UserFriendRequestsDisplay userFriendRequestsDisplay;
    private UserGroupRequestsDisplay userGroupRequestsDisplay;
    
    private final User currentUser;
    private int userId;
    
    private final DataBaseRequestLoader dataBaseRequestLoader = new DataBaseRequestLoader();
    private final DatabaseBalanceLogger dataBaseUserBalanceLogger = new DatabaseBalanceLogger();
    private final DataBaseUserLoader dataBaseUserLoader = new DataBaseUserLoader();
    private final DataBaseGroupLoader dataBaseGroupLoader = new DataBaseGroupLoader();

    UserRequestsControl(UserMainDisplay userMainDisplay, User loggedUser) {
        this.currentUser = loggedUser;
        this.userMainDisplay = userMainDisplay;
        userMainDisplay.on(setUserMainDisplayRequestsEvents());
    }

    private UserMainDisplay.RequestEvents setUserMainDisplayRequestsEvents() {
        return new UserMainDisplay.RequestEvents() {

            @Override
            public void openFriendRequestsWindow() {
                userFriendRequestsDisplay = new UserFriendRequestsDisplay();
                userFriendRequestsDisplay.on(setUserFriendRequestsDisplayEvents());
                userFriendRequestsDisplay.setFriendRequests();
                userFriendRequestsDisplay.setVisible(true);
            }

            @Override
            public void openGroupRequestsWindow() {
                userGroupRequestsDisplay = new UserGroupRequestsDisplay();
                userGroupRequestsDisplay.on(setUserGroupRequestsDisplayEvents());
                userGroupRequestsDisplay.setGroupRequests();
                userGroupRequestsDisplay.setVisible(true);
            }
        };
    }
    private UserFriendRequestsDisplay.Events setUserFriendRequestsDisplayEvents() {
        return new UserFriendRequestsDisplay.Events() {
            @Override
            public ArrayList<Request> getRequests(char requestType) {
                return dataBaseRequestLoader.getRequests(currentUser.getId(), requestType);
            }

            @Override
            public void acceptRequest(Request request) {
                dataBaseRequestLoader.acceptRequest(request);
                userFriendRequestsDisplay.setFriendRequests();
            }

            @Override
            public void discardRequest(Request request) {
                dataBaseRequestLoader.discardRequest(request);
                userFriendRequestsDisplay.setFriendRequests();
            }

            @Override
            public User getUser(int userId) {
                return dataBaseUserLoader.loadUser(userId);
            }

            @Override
            public Group getGroup(int groupId) {
                return null;
            }
        };
    }
    private UserGroupRequestsDisplay.Events setUserGroupRequestsDisplayEvents() {
        return new UserGroupRequestsDisplay.Events() {
            
            @Override
            public ArrayList<Request> getRequests(char requestType) {
                userId = currentUser.getId();
                return dataBaseRequestLoader.getRequests(userId, requestType);
            }

            @Override
            public void acceptRequest(Request request) {
                dataBaseRequestLoader.acceptRequest(request);
                Balance userBalance = new Balance(userId,request.getSourceId());
                dataBaseUserBalanceLogger.save(userBalance);
                userGroupRequestsDisplay.setGroupRequests();
            }

            @Override
            public void discardRequest(Request request) {
                dataBaseRequestLoader.discardRequest(request);
                userGroupRequestsDisplay.setGroupRequests();
            }

            @Override
            public User getUser(int userId) {
                return dataBaseUserLoader.loadUser(userId);
            }

            @Override
            public Group getGroup(int groupId) {
                return dataBaseGroupLoader.load(groupId);
            }
        };
    }
}
