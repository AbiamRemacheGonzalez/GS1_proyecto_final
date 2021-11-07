package GS1.App;

import GS1.App.UserLoginAndSignUp.DataBaseUserLoader;
import GS1.App.UserLoginAndSignUp.UserLoginDisplay;
import GS1.Control.UserAccessControl;

public class Main {
    UserLoginDisplay userLoginDisplay;
    DataBaseUserLoader userloader;
    public static void main(String[] args) {
        new Main().execute();
    }
    public Main(){
        userLoginDisplay = new UserLoginDisplay();
        userloader = new DataBaseUserLoader();
        new UserAccessControl(userLoginDisplay,userloader);
    }

    private void execute() {
        userLoginDisplay.setVisible(true);
    }
    
    
}
