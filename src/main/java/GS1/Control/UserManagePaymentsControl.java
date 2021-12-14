package GS1.Control;

import GS1.App.CreateGroup.DataBaseGroupLoader;
import GS1.App.Group.EditGroupDisplay;
import GS1.App.ManagePayments.DatabasePaymentLoader;
import GS1.App.UserLoginAndSignUp.DataBaseUserLoader;
import GS1.App.ManagePayments.ManagePaymentsDisplay;
import GS1.App.ManagePayments.DatabasePaymentLogger;
import GS1.Model.Group;
import GS1.Model.Payment;
import GS1.Model.User;
import java.util.ArrayList;

public class UserManagePaymentsControl {
    private ManagePaymentsDisplay managePaymentsDisplay;
    private final DataBaseUserLoader databaseUserLoader = new DataBaseUserLoader();
    private DatabasePaymentLogger databasePaymentLogger = new DatabasePaymentLogger();
    private DatabasePaymentLoader databasePaymentLoader = new DatabasePaymentLoader();
    private final DataBaseGroupLoader dataBaseGroupLoader = new DataBaseGroupLoader();
    
    private User currentUser;
    private Group currentGroup;
    private EditGroupDisplay editGroupDisplay;
    private ArrayList<User> currentGroupMembers;
    private Payment currentPayment;
    
    public UserManagePaymentsControl(EditGroupDisplay editGroupDisplay,User currentUser,Group currentGroup) {
        this.currentUser = currentUser;
        this.editGroupDisplay = editGroupDisplay;
        this.currentGroup = currentGroup;
        editGroupDisplay.on(setPaymentsEventsDiplayEvents());
    }
    
    private EditGroupDisplay.PaymentsEvents setPaymentsEventsDiplayEvents(){
        return new EditGroupDisplay.PaymentsEvents() {
            @Override
            public void openManagePaymentDisplay() {
                managePaymentsDisplay = new ManagePaymentsDisplay(currentUser,currentGroup);
                managePaymentsDisplay.on(setManagePaymentsDisplayEvents());
                managePaymentsDisplay.setVisible(true);
                managePaymentsDisplay.setMembersList();
            }
        };
    }
    private ManagePaymentsDisplay.ManagePaymentsEvents setManagePaymentsDisplayEvents(){
        return new ManagePaymentsDisplay.ManagePaymentsEvents(){
            @Override
            public void savePayment(Payment payment) {
                databasePaymentLogger.save(payment);
                payment.setPaymentId(databasePaymentLoader.getPaymentId(payment));
                currentPayment = payment;
                currentGroup.addPayment(payment);
                generateChunckPayments();
            }
            
            @Override
            public boolean checkNewPaymentValues(String title, double amount) {
                managePaymentsDisplay.resetLabels();
                boolean res = true;
                if(title.isEmpty()){
                    res=false;
                    managePaymentsDisplay.printErrorTitle();
                }
                if(amount <= 0){
                    res=false;
                    managePaymentsDisplay.printAmountError();
                }
                return res;
            }

            @Override
            public ArrayList<User> getMembers() {
                currentGroupMembers = dataBaseGroupLoader.getGroupMembers(currentGroup.getIdGroup());
                return currentGroupMembers;
            }

            @Override
            public User getUser(int userId) {
                return databaseUserLoader.loadUser(userId);
            }
        };
    }
    private void generateChunckPayments() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
