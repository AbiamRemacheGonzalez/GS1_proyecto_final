package GS1.App;

import GS1.App.UserLoginAndSignUp.DataBasePaymentMethodLogger;
import GS1.App.UserLoginAndSignUp.DataBaseUserLoader;
import GS1.App.UserLoginAndSignUp.DataBaseUserLogger;
import GS1.App.UserLoginAndSignUp.UserLoginDisplay;
import GS1.Control.UserAccessControl;

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
        userloader = new DataBaseUserLoader();
        userLogger = new DataBaseUserLogger();
        paymentMethodLogger = new DataBasePaymentMethodLogger();
        new UserAccessControl(userLoginDisplay,userloader,userLogger,paymentMethodLogger);
    }

    private void execute() {
        userLoginDisplay.setVisible(true);
    }
    
    
}
