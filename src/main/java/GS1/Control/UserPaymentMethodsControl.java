package GS1.Control;

import GS1.App.Requests.UserFriendRequestsDisplay;
import GS1.Persistence.PaymentMethod.DataBasePaymentMethodLogger;
import GS1.App.AddNewPaymentMethod.AddBizumPaymentDisplay;
import GS1.App.AddNewPaymentMethod.AddCreditCardPaymentDisplay;
import GS1.App.AddNewPaymentMethod.AddPaypalPaymentDisplay;
import GS1.Persistence.User.DataBaseUserLoader;
import GS1.App.UserMainDisplay;
import GS1.Model.PaymentsMethods.BizumPaymentMethod;
import GS1.Model.PaymentsMethods.PaymentMethod;
import GS1.Model.PaymentsMethods.PaypalPaymentMethod;
import GS1.Model.User;
import GS1.App.SearchUser.SearchUsersDisplay;
import GS1.Model.PaymentsMethods.CreditCardPaymentMethod;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserPaymentMethodsControl {
    private final UserMainDisplay userMainDisplay;
    private AddBizumPaymentDisplay newBizumPaymentDisplay;
    private AddCreditCardPaymentDisplay newCreditCardDisplay;
    private AddPaypalPaymentDisplay newPaypalPaymentDisplay;
    private SearchUsersDisplay searchFriendDisplay;
    private UserSearchControl searchUserControl;
    private UserFriendRequestsDisplay friendRequestDisplay;
    
    private final DataBasePaymentMethodLogger paymentMethodLogger = new DataBasePaymentMethodLogger();
    private final DataBaseUserLoader userLoader = new DataBaseUserLoader();
    private final User currentUser;
    
    private final String mailPattern = "^(.+)@(.+)$";
    private final String numberPattern = "[0-9]{9}";
    private final String creditNumberPattern = "(4[0-9]{12}(?:[0-9]{3})?)|(5[1-5][0-9]{14})";
    private final String expiryDatePattern = "(([0][1-9]|1[1-2])/([0-2][0-9]|3[0-1]))";

    public UserPaymentMethodsControl(UserMainDisplay userMainDisplay, User user) {
        this.currentUser = user;
        this.userMainDisplay = userMainDisplay;
        userMainDisplay.on(setUserMainPaymentEvents());
    }

    private UserMainDisplay.PaymentEvents setUserMainPaymentEvents() {
        return new UserMainDisplay.PaymentEvents() {
            @Override
            public void openAddCreditCardWindow() {
                newCreditCardDisplay = new AddCreditCardPaymentDisplay(currentUser);
                newCreditCardDisplay.on(setNewCreditCardDisplayEvents(), 1);
                newCreditCardDisplay.setVisible(true);
            }

            @Override
            public void openAddPaypalWindow() {
                newPaypalPaymentDisplay = new AddPaypalPaymentDisplay();
                newPaypalPaymentDisplay.on(setNewPaypalPaymentDisplayEvents());
                newPaypalPaymentDisplay.setVisible(true);
            }

            @Override
            public void openAddBizumWindow() {
                newBizumPaymentDisplay = new AddBizumPaymentDisplay();
                newBizumPaymentDisplay.on(setNewBizumPaymentDisplayEvents());
                newBizumPaymentDisplay.setVisible(true);
            }
        };
    }
    
    private AddCreditCardPaymentDisplay.Events setNewCreditCardDisplayEvents() {
        return new AddCreditCardPaymentDisplay.Events() {
            @Override
            public void signUp(User user, CreditCardPaymentMethod credit) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void addPayment(CreditCardPaymentMethod credit) {
                internalAddPayment(credit);
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

            @Override
            public void openUserRegistrationWindow() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void openUserMainWindow() {
                userMainDisplay.setVisible(true);
            }
        };
    }
    private AddPaypalPaymentDisplay.Events setNewPaypalPaymentDisplayEvents() {
        return new AddPaypalPaymentDisplay.Events() {
            @Override
            public void addPayment(PaypalPaymentMethod paypal) {
                internalAddPayment(paypal);
            }

            @Override
            public Boolean checkEmail(String email) {
                Boolean res = true;
                if(!emailPatternCheck(email)){
                    res = false;
                    newPaypalPaymentDisplay.printEmailPatternError();
                }
                return res;
            }

            @Override
            public void openUserMainWindow() {
                userMainDisplay.setVisible(true);
            }
        };
    }
    private AddBizumPaymentDisplay.Events setNewBizumPaymentDisplayEvents() {
        return new AddBizumPaymentDisplay.Events() {
            @Override
            public void addPayment(BizumPaymentMethod bizum) {
                internalAddPayment(bizum);
            }

            @Override
            public Boolean checkTelephoneNumber(String telephone) {
                Boolean res = true;
                if(!telephonePatternCheck(telephone)){
                    res = false;
                    newBizumPaymentDisplay.printTelephonePatternError();
                }
                return res;
            }

            @Override
            public void openUserMainWindow() {
                userMainDisplay.setVisible(true);
            }
        };
    }
    private boolean isCorrectExpiryDate(String expiryDate){
        Pattern pattern = Pattern.compile(expiryDatePattern);
        Matcher matcher = pattern.matcher(expiryDate);
        return matcher.matches();
    }
    private boolean isCorrectCreditNumber(long creditNumber){
        Pattern pattern = Pattern.compile(creditNumberPattern);
        Matcher matcher = pattern.matcher(Long.toString(creditNumber));
        return matcher.matches();
    }
    private boolean emailPatternCheck(String mail){
        Pattern pattern = Pattern.compile(mailPattern);
        Matcher matcher = pattern.matcher(mail);
        return matcher.matches();
    }
    private boolean telephonePatternCheck(String telephone) {
        Pattern pattern = Pattern.compile(numberPattern);
        Matcher matcher = pattern.matcher(telephone);
        return matcher.matches();
    }
    private void internalAddPayment(PaymentMethod payment){
        //int userId = userLoader.loadUserId(currentUser.getMail(), currentUser.getPassword());
        paymentMethodLogger.save(currentUser.getId(), payment);
        currentUser.addPaymentMethod(payment);
    }
    
}
