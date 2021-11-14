package GS1.Control;

import GS1.App.AddNewPaymentMethod.NewBizumPaymentDisplay;
import GS1.App.AddNewPaymentMethod.NewCreditCardPaymentDisplay;
import GS1.App.AddNewPaymentMethod.NewPaypalPaymentDisplay;
import GS1.App.AddNewPaymentMethod.DataBasePaymentMethodLogger;
import GS1.App.UserLoginAndSignUp.DataBaseUserLoader;
import GS1.App.UserLoginAndSignUp.UserLoginDisplay;
import GS1.App.UserMainDisplay;
import GS1.Model.Payments.BizumPaymentMethod;
import GS1.Model.Payments.CreditCardPaymentMethod;
import GS1.Model.Payments.PaymentMethod;
import GS1.Model.Payments.PaypalPaymentMethod;
import GS1.Model.User;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserPaymentsControl {
    private UserLoginDisplay userLoginDisplay;
    private final UserMainDisplay userMainDisplay;
    private NewBizumPaymentDisplay newBizumPaymentDisplay;
    private NewCreditCardPaymentDisplay newCreditCardDisplay;
    private NewPaypalPaymentDisplay newPaypalPaymentDisplay;
    
    private final DataBasePaymentMethodLogger paymentMethodLogger = new DataBasePaymentMethodLogger();
    private final DataBaseUserLoader userLoader = new DataBaseUserLoader();
    private final User currentUser;
    
    private final String mailPattern = "^(.+)@(.+)$";
    private final String numberPattern = "[0-9]{9}";
    private final String creditNumberPattern = "(4[0-9]{12}(?:[0-9]{3})?)|(5[1-5][0-9]{14})";
    private final String expiryDatePattern = "(([0][1-9]|1[1-2])/([0-2][0-9]|3[0-1]))";

    public UserPaymentsControl(UserMainDisplay userMainDisplay, User user) {
        this.currentUser = user;
        this.userMainDisplay = userMainDisplay;
        userMainDisplay.on(setUserMainDisplayEvents());
    }

    private UserMainDisplay.Events setUserMainDisplayEvents() {
        return new UserMainDisplay.Events() {
            @Override
            public void openNewCreditCardWindow() {
                newCreditCardDisplay = new NewCreditCardPaymentDisplay(currentUser);
                newCreditCardDisplay.on(setNewCreditCardDisplayEvents(), 1);
                newCreditCardDisplay.setVisible(true);
            }

            @Override
            public void openNewPaypalWindow() {
                newPaypalPaymentDisplay = new NewPaypalPaymentDisplay();
                newPaypalPaymentDisplay.on(setNewPaypalPaymentDisplayEvents());
                newPaypalPaymentDisplay.setVisible(true);
            }

            @Override
            public void openNewBizumWindow() {
                newBizumPaymentDisplay = new NewBizumPaymentDisplay();
                newBizumPaymentDisplay.on(setNewBizumPaymentDisplayEvents());
                newBizumPaymentDisplay.setVisible(true);
            }
        };
    }
    
    private NewCreditCardPaymentDisplay.Events setNewCreditCardDisplayEvents() {
        return new NewCreditCardPaymentDisplay.Events() {
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
    private NewPaypalPaymentDisplay.Events setNewPaypalPaymentDisplayEvents() {
        return new NewPaypalPaymentDisplay.Events() {
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
    private NewBizumPaymentDisplay.Events setNewBizumPaymentDisplayEvents() {
        return new NewBizumPaymentDisplay.Events() {
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
        int userId = userLoader.loadUserId(currentUser.getMail(), currentUser.getPassword());
        paymentMethodLogger.save(userId, payment);
        currentUser.addPaymentMethod(payment);
    }
    
}
