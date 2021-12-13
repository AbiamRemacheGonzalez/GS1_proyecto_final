package GS1.Control;

import GS1.App.AddNewPaymentMethod.DataBasePaymentMethodLogger;
import GS1.App.AddNewPaymentMethod.AddCreditCardPaymentDisplay;
import GS1.App.CreateGroup.DataBaseGroupLogger;
import GS1.App.UserLoginAndSignUp.DataBaseUserLoader;
import GS1.App.UserLoginAndSignUp.DataBaseUserLogger;
import GS1.App.UserLoginAndSignUp.UserLoginDisplay;
import GS1.App.UserMainDisplay;
import GS1.Model.PaymentsMethods.CreditCardPaymentMethod;
import GS1.Model.User;
import GS1.App.UserLoginAndSignUp.UserRegistrationDisplay;
import GS1.Model.Group;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserAccessControl {

    public static User loggedUser;

    private UserLoginDisplay userLoginDisplay;
    private UserRegistrationDisplay userRegistrationDisplay;
    private UserMainDisplay userMainDisplay;
    private AddCreditCardPaymentDisplay newCreditCardDisplay;

    private final DataBaseUserLoader databaseUserLoader = new DataBaseUserLoader();
    private final DataBaseUserLogger databaseUserLogger = new DataBaseUserLogger();
    private final DataBaseGroupLogger databaseGroupLogger = new DataBaseGroupLogger();
    private final DataBasePaymentMethodLogger databasePaymentMethodLogger = new DataBasePaymentMethodLogger();

    //Clases de control que gestionan los eventos del User Main
    private UserPaymentMethodsControl userPaymentControl;
    private UserGroupControl userGroupControl;
    private UserSearchControl userSearchControl;
    private UserRequestsControl userRequestControl;

    private final String mailPattern = "^(.+)@(.+)$";
    private final String creditNumberPattern = "(4[0-9]{12}(?:[0-9]{3})?)|(5[1-5][0-9]{14})";
    private final String expiryDatePattern = "(([0][1-9]|1[1-2])/([0-2][0-9]|3[0-1]))";

    public UserAccessControl(UserLoginDisplay userLoginDisplay) {
        this.userLoginDisplay = userLoginDisplay;
        this.userLoginDisplay.on(setUserLoginDisplayEvents());
    }

    public UserLoginDisplay.LoginEvents setUserLoginDisplayEvents() {
        return new UserLoginDisplay.LoginEvents() {
            @Override
            public void login(String mail, String pass) {
                userLoginDisplay.resetErrorPrints();
                databaseUserLoader.initialize(mail, pass);
                loggedUser = databaseUserLoader.load();
                enableUserMainDisplay();
            }

            @Override
            public void openSignUpWindow() {
                userRegistrationDisplay = new UserRegistrationDisplay();
                userRegistrationDisplay.on(setUserRegistrationDisplayEvents());
                userRegistrationDisplay.setVisible(true);
            }

            @Override
            public boolean checkUser(String mail, String pass) {
                userLoginDisplay.resetErrorPrints();
                databaseUserLoader.initialize(mail, pass);
                User loggedUser = databaseUserLoader.load();
                if (loggedUser == null) {
                    if (!databaseUserLoader.isEmailPatternCorrect()) {
                        userLoginDisplay.printEmailPatternError();
                    }
                    if (!databaseUserLoader.emailExists()) {
                        userLoginDisplay.printEmailNotFoundError();
                    }
                    if (!databaseUserLoader.isPasswordCorrect()) {
                        userLoginDisplay.printPasswordError();
                    }
                    return false;
                }
                return true;
            }
        };
    }

    private UserRegistrationDisplay.RegistrationEvents setUserRegistrationDisplayEvents() {
        return new UserRegistrationDisplay.RegistrationEvents() {
            @Override
            public void openNewCreditCardWindow(User user) {
                newCreditCardDisplay = new AddCreditCardPaymentDisplay(user);
                newCreditCardDisplay.on(setAddCreditCardDisplayEvents(), 0);
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
                if (firstName.equals("")) {
                    res = false;
                    userRegistrationDisplay.printFirstNameError();
                }
                if (lastName.equals("")) {
                    res = false;
                    userRegistrationDisplay.printLastNameError();
                }
                if (!emailPatternCheck(email)) {
                    res = false;
                    userRegistrationDisplay.printEmailPatternError();
                }
                if (databaseUserLoader.emailUserExists(email)) {
                    res = false;
                    userRegistrationDisplay.printExistEmailError();
                }
                if (password.length() < 4) {
                    res = false;
                    userRegistrationDisplay.printPasswordLimitError();
                }
                if (!password.equals(confirmPassword)) {
                    res = false;
                    userRegistrationDisplay.printConfirmPasswordAreNotEqualError();
                }
                return res;
            }

            private boolean emailPatternCheck(String mail) {
                Pattern pattern = Pattern.compile(mailPattern);
                Matcher matcher = pattern.matcher(mail);
                return matcher.matches();
            }

        };
    }

    private AddCreditCardPaymentDisplay.Events setAddCreditCardDisplayEvents() {
        return new AddCreditCardPaymentDisplay.Events() {
            @Override
            public void signUp(User user, CreditCardPaymentMethod credit) {
                //int userId = databaseUserLoader.loadUserId(user.getMail(), user.getPassword());
                if(databaseUserLogger.save(user) && databasePaymentMethodLogger.save(databaseUserLoader.loadUserId(user.getMail()), credit)){
                    user.addPaymentMethod(credit);
                    loggedUser = user;
                    enableUserMainDisplay();
                }
            }

            @Override
            public void openUserRegistrationWindow() {
                userRegistrationDisplay.setVisible(true);
            }

            @Override
            public Boolean checkCreditValues(String owner, long creditNumber, Date expiryDate) {
                Boolean res = true;
                newCreditCardDisplay.resetErrorPrints();
                if (owner.isEmpty()) {
                    res = false;
                    newCreditCardDisplay.printOwnerError();
                }
                if (!isCorrectCreditNumber(creditNumber)) {
                    res = false;
                    newCreditCardDisplay.printCreditNumberError();
                }
                SimpleDateFormat format = new SimpleDateFormat("MM/yy");
                String dateString = format.format(expiryDate);
                Date current = new Date();

                if (!isCorrectExpiryDate(dateString)) {
                    res = false;
                    newCreditCardDisplay.printExpiryDateError();
                } else if (!current.before(expiryDate)) {
                    res = false;
                    newCreditCardDisplay.printCreditExpiredError();
                }
                return res;
            }

            private boolean isCorrectCreditNumber(long creditNumber) {
                Pattern pattern = Pattern.compile(creditNumberPattern);
                Matcher matcher = pattern.matcher(Long.toString(creditNumber));
                return matcher.matches();
            }

            private boolean isCorrectExpiryDate(String expiryDate) {
                Pattern pattern = Pattern.compile(expiryDatePattern);
                Matcher matcher = pattern.matcher(expiryDate);
                return matcher.matches();
            }

            @Override
            public void addPayment(CreditCardPaymentMethod credit) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void openUserMainWindow() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
    }

    private void enableUserMainDisplay() {
        if (loggedUser != null) {
            userMainDisplay = new UserMainDisplay();
            userMainDisplay.on(setUserMainDisplayMainEvents());
            //Delegamos la gestión de eventos del userMainDisplay a sus ficheros de control correspondiente.
            userGroupControl = new UserGroupControl(userMainDisplay, loggedUser);
            userPaymentControl = new UserPaymentMethodsControl(userMainDisplay, loggedUser);
            userSearchControl = new UserSearchControl(userMainDisplay, loggedUser);
            userRequestControl = new UserRequestsControl(userMainDisplay, loggedUser);
            
            userMainDisplay.setGroupList();
            userMainDisplay.setVisible(true);
        }
    }

    private UserMainDisplay.MainEvents setUserMainDisplayMainEvents() {
        return new UserMainDisplay.MainEvents() {
            @Override
            public void openLoginDisplay() {
                userLoginDisplay = new UserLoginDisplay();
                userLoginDisplay.on(setUserLoginDisplayEvents());
                userLoginDisplay.setVisible(true);
            }

            @Override
            public ArrayList<Group> getGroups() {
                return databaseGroupLogger.getGroups(loggedUser.getId());
            }
        };
    }
}
