package GS1.Control;

import GS1.App.UserLoginAndSignUp.DataBaseUserLoader;
import GS1.App.UserLoginAndSignUp.NewCreditCardDisplay;
import GS1.App.UserLoginAndSignUp.UserLoginDisplay;
import GS1.App.UserLoginAndSignUp.UserMainDisplay;
import GS1.App.UserLoginAndSignUp.UserRegistrationDisplay;
import GS1.Model.CreditCardPaymentMethod;
import GS1.Model.User;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserAccessControl {
    private final UserLoginDisplay userLoginDisplay;
    private final DataBaseUserLoader userLoader;
    private UserRegistrationDisplay userRegistrationDisplay;
    private NewCreditCardDisplay newCreditCardDisplay;
    private final String mailPattern = "^(.+)@(.+)$";

    public UserAccessControl(UserLoginDisplay userLoginDisplay, DataBaseUserLoader userLoader) {
        this.userLoginDisplay = userLoginDisplay;
        this.userLoader = userLoader;
        this.userLoginDisplay.on(setUserLoginDisplayEvents());
    }
    
    private UserLoginDisplay.Events setUserLoginDisplayEvents(){
        return new UserLoginDisplay.Events() {
            @Override
            public void login(String mail, String pass) {
                userLoginDisplay.resetErrorPrints();
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

            @Override
            public void openSignUpWindow() {
                userLoginDisplay.dispose();
                userRegistrationDisplay = new UserRegistrationDisplay();
                userRegistrationDisplay.on(setUserRegistrationDisplayEvents());
                userRegistrationDisplay.setVisible(true);
            }
        };
    }
    private UserRegistrationDisplay.Events setUserRegistrationDisplayEvents(){
        return new UserRegistrationDisplay.Events(){
            @Override
            public void openNewCreditCardWindow(User user) {
                userRegistrationDisplay.dispose();
                newCreditCardDisplay = new NewCreditCardDisplay();
                newCreditCardDisplay.setUser(user);
                newCreditCardDisplay.on(setNewCreditCardDisplayEvents());
                newCreditCardDisplay.setVisible(true);
            }

            @Override
            public void openUserLoginWindow() {
                newCreditCardDisplay.dispose();
                userLoginDisplay.on(setUserLoginDisplayEvents());
                userLoginDisplay.setVisible(true);
            }

            @Override
            public Boolean checkNewUserValues(String firstName, String lastName, String email, String password, String confirmPassword) {
                Boolean res = true;
                userRegistrationDisplay.resetErrorPrints();
                if(firstName.equals("")){
                    res = false;
                    userRegistrationDisplay.printFirstNameError();
                }
                if(lastName.equals("")){
                    res = false;
                    userRegistrationDisplay.printLastNameError();
                }
                if(!emailPatternCheck(email)){
                    res = false;
                    userRegistrationDisplay.printEmailPatternError();
                }
                if(userLoader.emailUserExists(email)){
                    res = false;
                    userRegistrationDisplay.printExistEmailError();
                }
                if(password.length()<4){
                    res = false;
                    userRegistrationDisplay.printPasswordLimitError();
                }
                if(!password.equals(confirmPassword)){
                    res = false;
                    userRegistrationDisplay.printConfirmPasswordAreNotEqualError();
                }
                return res;
            }
            
        };
    }
    
    private NewCreditCardDisplay.Events setNewCreditCardDisplayEvents(){
        return new NewCreditCardDisplay.Events(){
            @Override
            public void signUp(User user, CreditCardPaymentMethod credit) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void openUserRegistrationWindow() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
    }
    
    private boolean emailPatternCheck(String mail){
        Pattern pattern = Pattern.compile(mailPattern);
        Matcher matcher = pattern.matcher(mail);
        return matcher.matches();
    }
}
