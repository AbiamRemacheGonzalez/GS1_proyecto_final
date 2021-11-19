package main.java.GS1.App;

import main.java.GS1.App.AddNewPaymentMethod.DataBasePaymentMethodLogger;
import main.java.GS1.App.UserLoginAndSignUp.DataBaseUserLoader;
import main.java.GS1.App.UserLoginAndSignUp.DataBaseUserLogger;
import main.java.GS1.App.UserLoginAndSignUp.UserLoginDisplay;
import main.java.GS1.Control.UserAccessControl;

public class Main {
    UserLoginDisplay userLoginDisplay;
    DataBaseUserLoader userloader;
    DataBaseUserLogger userLogger;
    DataBasePaymentMethodLogger paymentMethodLogger;
    public static void main(String[] args) {
        new Main().execute();
    }
    public Main(){
        userLoginDisplay = new UserLoginDisplay();
        new UserAccessControl(userLoginDisplay);
    }

    private void execute() {
        userLoginDisplay.setVisible(true);
    }
    
    
}
