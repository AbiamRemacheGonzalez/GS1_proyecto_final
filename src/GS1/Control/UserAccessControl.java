package GS1.Control;

import GS1.App.UserLoginAndSignUp.DataBasePaymentMethodLogger;
import GS1.App.UserLoginAndSignUp.DataBaseUserLoader;
import GS1.App.UserLoginAndSignUp.DataBaseUserLogger;
import GS1.App.AddNewPaymentMethod.NewCreditCardPaymentDisplay;
import GS1.App.UserLoginAndSignUp.UserLoginDisplay;
import GS1.App.UserMainDisplay;
import GS1.App.UserLoginAndSignUp.UserRegistrationDisplay;
import GS1.Model.Payments.CreditCardPaymentMethod;
import GS1.Model.User;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserAccessControl {
    private UserLoginDisplay userLoginDisplay;
    private final DataBaseUserLoader userLoader;
    private final DataBaseUserLogger userLogger;
    private final DataBasePaymentMethodLogger paymentMethodLogger;
    private UserRegistrationDisplay userRegistrationDisplay;
    private NewCreditCardPaymentDisplay newCreditCardDisplay;
    private final String mailPattern = "^(.+)@(.+)$";
    private final String creditNumberPattern = "(4[0-9]{12}(?:[0-9]{3})?)|(5[1-5][0-9]{14})";
    private final String expiryDatePattern = "(([0][1-9]|1[1-2])/([0-2][0-9]|3[0-1]))";

    public UserAccessControl(UserLoginDisplay userLoginDisplay, DataBaseUserLoader userLoader, DataBaseUserLogger userLogger, DataBasePaymentMethodLogger paymentMethodLogger) {
        this.userLoginDisplay = userLoginDisplay;
        this.userLoader = userLoader;
        this.userLogger = userLogger;
        this.paymentMethodLogger = paymentMethodLogger;
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
                    new UserMainDisplay().setVisible(true);
                }
            }

            @Override
            public void openSignUpWindow() {
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
                newCreditCardDisplay = new NewCreditCardPaymentDisplay();
                newCreditCardDisplay.setUser(user);
                newCreditCardDisplay.on(setNewCreditCardDisplayEvents());
                newCreditCardDisplay.setVisible(true);
            }

            @Override
            public void openUserLoginWindow() {
                userLoginDisplay = new UserLoginDisplay();
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
    
    private NewCreditCardPaymentDisplay.Events setNewCreditCardDisplayEvents(){
        return new NewCreditCardPaymentDisplay.Events(){
            @Override
            public void signUp(User user, CreditCardPaymentMethod credit) {
                userLogger.save(user);
                int userId = userLoader.loadUserId(user.getMail(), user.getPassword());
                paymentMethodLogger.save(userId, credit);
                user.addPaymentMethod(credit);
                new UserMainDisplay().setVisible(true);
            }

            @Override
            public void openUserRegistrationWindow() {
                userRegistrationDisplay.setVisible(true);
            }

            @Override
            public Boolean checkCreditValues(String owner, long creditNumber, Date expiryDate) {
                Boolean res = true;
                newCreditCardDisplay.resetErrorPrints();
                if(owner.isEmpty()) {
                    res = false;
                    newCreditCardDisplay.printOwnerError();
                }
                if(!isCorrectCreditNumber(creditNumber)) {
                    res = false;
                    newCreditCardDisplay.printCreditNumberError();
                }
                SimpleDateFormat format = new SimpleDateFormat("MM/yy");
                String dateString = format.format(expiryDate);
                Date current = new Date();
                
                if(!isCorrectExpiryDate(dateString)){
                    res = false;
                    newCreditCardDisplay.printExpiryDateError();
                }else if(!current.before(expiryDate)){
                    res = false;
                    newCreditCardDisplay.printCreditExpiredError();
                }
                return res;
            }
        };
    }
    
    private boolean emailPatternCheck(String mail){
        Pattern pattern = Pattern.compile(mailPattern);
        Matcher matcher = pattern.matcher(mail);
        return matcher.matches();
    }
    
    private boolean isCorrectCreditNumber(long creditNumber){
        Pattern pattern = Pattern.compile(creditNumberPattern);
        Matcher matcher = pattern.matcher(Long.toString(creditNumber));
        return matcher.matches();
    }
    private boolean isCorrectExpiryDate(String expiryDate){
        Pattern pattern = Pattern.compile(expiryDatePattern);
        Matcher matcher = pattern.matcher(expiryDate);
        return matcher.matches();
    }
}
