package GS1.Control;

import GS1.App.Requests.DataBaseRequestLoader;
import GS1.App.Requests.UserRequestsDisplay;
import GS1.App.UserLoginAndSignUp.DataBaseUserLoader;
import GS1.App.UserMainDisplay;
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
                userRequestsDisplay.setVisible(true);
            }
        };
    }
    private UserRequestsDisplay.Events setUserRequestsDisplayEvents() {
        return new UserRequestsDisplay.Events() {
            @Override
            public ArrayList<String> getUserResquests() {
                int userId = dataBaseUserLoader.loadUserId(currentUser.getMail(), currentUser.getPassword());
                return dataBaseRequestLoader.getResquests(userId);
            }

            @Override
            public void acceptRequest(int destinationUserId) {
                int sourceUserId = dataBaseUserLoader.loadUserId(currentUser.getMail(), currentUser.getPassword());
                dataBaseRequestLoader.acceptRequest(sourceUserId, destinationUserId);
            }

            @Override
            public void discardRequest(int destinationUserId) {
                int sourceUserId = dataBaseUserLoader.loadUserId(currentUser.getMail(), currentUser.getPassword());
                dataBaseRequestLoader.discardRequest(sourceUserId, destinationUserId);
            }
        };
    }
}
