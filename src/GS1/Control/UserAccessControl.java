package GS1.Control;

import GS1.App.UserLoginAndSignUp.DataBaseUserLoader;
import GS1.App.UserLoginAndSignUp.UserLoginDisplay;
import GS1.App.UserLoginAndSignUp.UserMainDisplay;
import GS1.Model.User;

public class UserAccessControl {
    private final UserLoginDisplay userLoginDisplay;
    private final DataBaseUserLoader userLoader;

    public UserAccessControl(UserLoginDisplay userLoginDisplay, DataBaseUserLoader userLoader) {
        this.userLoginDisplay = userLoginDisplay;
        this.userLoader = userLoader;
        this.userLoginDisplay.on(event());
    }
    
    private UserLoginDisplay.Events event(){
        return new UserLoginDisplay.Events() {
            @Override
            public void login(String mail, String pass) {
                userLoader.initialize(mail, pass);
                User login = userLoader.load();
                if(login == null){
                    if(!userLoader.isEmailPatternCorrect())userLoginDisplay.printEmailPatternError();
                    if(!userLoader.emailExists())userLoginDisplay.printEmailNotFoundError();
                    if(!userLoader.isPasswordCorrect())userLoginDisplay.printPasswordError();
                }else{
                    userLoginDisplay.dispose();
                    new UserMainDisplay().setVisible(true);
                }
            }
        };
    }
}
