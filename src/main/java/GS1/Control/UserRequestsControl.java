package GS1.Control;

import GS1.App.Requests.DataBaseRequestLoader;
import GS1.App.Requests.UserRequestsDisplay;
import GS1.App.UserLoginAndSignUp.DataBaseUserLoader;
import GS1.App.UserMainDisplay;
import GS1.Model.Group;
import GS1.Model.Request;
import GS1.Model.User;
import java.util.ArrayList;

public class UserRequestsControl {
    private final UserMainDisplay userMainDisplay;
    private UserRequestsDisplay userRequestsDisplay;
    
    private final User currentUser;    
    
    private final DataBaseRequestLoader dataBaseRequestLoader = new DataBaseRequestLoader();
    private final DataBaseUserLoader dataBaseUserLoader = new DataBaseUserLoader();

    UserRequestsControl(UserMainDisplay userMainDisplay, User loggedUser) {
        this.currentUser = loggedUser;
        this.userMainDisplay = userMainDisplay;
        userMainDisplay.on(setUserMainDisplayRequestsEvents());
    }

    private UserMainDisplay.RequestEvents setUserMainDisplayRequestsEvents() {
        return new UserMainDisplay.RequestEvents() {
            @Override
            public void openRequestsWindow() {
                userRequestsDisplay = new UserRequestsDisplay();
                userRequestsDisplay.on(setUserRequestsDisplayEvents());
                userRequestsDisplay.initLists();
                userRequestsDisplay.setVisible(true);
            }
        };
    }
    private UserRequestsDisplay.Events setUserRequestsDisplayEvents() {
        return new UserRequestsDisplay.Events() {
            @Override
            public ArrayList<Request> getRequests(char requestType) {
                int userId = dataBaseUserLoader.loadUserId(currentUser.getMail(), currentUser.getPassword());
                return dataBaseRequestLoader.getRequests(userId, requestType);
            }

            @Override
            public void acceptRequest(Request request) {
                dataBaseRequestLoader.acceptRequest(request);
            }

            @Override
            public void discardRequest(Request request) {
                dataBaseRequestLoader.discardRequest(request);
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
}
