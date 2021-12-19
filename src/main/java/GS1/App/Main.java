package GS1.App;

import GS1.Persistence.User.DataBaseUserLoader;
import GS1.Persistence.User.DataBaseUserLogger;
import GS1.Persistence.PaymentMethod.DataBasePaymentMethodLogger;
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
        new UserAccessControl(userLoginDisplay);
    }

    private void execute() {
        userLoginDisplay.setVisible(true);
    }
    
    
}
