package GS1.Control;

import GS1.App.CreateGroup.DataBaseGroupLoader;
import GS1.App.Group.EditGroupDisplay;
import GS1.App.Group.GroupDisplay;
import GS1.App.ManagePayments.DatabaseBalanceLoader;
import GS1.App.ManagePayments.DatabaseChunkLogger;
import GS1.App.ManagePayments.DatabasePaymentLoader;
import GS1.App.UserLoginAndSignUp.DataBaseUserLoader;
import GS1.App.ManagePayments.ManagePaymentsDisplay;
import GS1.App.ManagePayments.DatabasePaymentLogger;
import GS1.App.PaymentGateway.PaymentGatewayDisplay;
import GS1.App.Requests.DatabaseUserBalanceLogger;
import GS1.Model.Balance;
import GS1.Model.ChunckPayment;
import GS1.Model.Group;
import GS1.Model.Payment;
import GS1.Model.User;
import java.util.ArrayList;
import java.util.List;

public class UserManagePaymentsControl {
    private ManagePaymentsDisplay managePaymentsDisplay;
    private final DataBaseUserLoader databaseUserLoader = new DataBaseUserLoader();
    private DatabasePaymentLogger databasePaymentLogger = new DatabasePaymentLogger();
    private DatabasePaymentLoader databasePaymentLoader = new DatabasePaymentLoader();
    private final DataBaseGroupLoader dataBaseGroupLoader = new DataBaseGroupLoader();
    private final DatabaseUserBalanceLogger databaseBalanceLogger = new DatabaseUserBalanceLogger();
    private DatabaseChunkLogger databaseChunkLogger = new DatabaseChunkLogger();
    private DatabaseBalanceLoader databaseBalanceLoader = new DatabaseBalanceLoader();
    
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
                managePaymentsDisplay = new ManagePaymentsDisplay();
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

            @Override
            public int getIdGroup() {
                return currentGroup.getIdGroup();
            }
        };
    }
    
        
                
    private void generateChunckPayments() {
        Double chunckAmount = currentPayment.getAmount()/currentGroupMembers.size();
        int userDestinationID = currentPayment.getUserDestinationID();
        for (User currentGroupMember : currentGroupMembers) {
            if(currentGroupMember.getId()!=userDestinationID){
                Balance userBalance = databaseBalanceLoader.getUserBalance(currentGroup.getIdGroup(), currentGroupMember.getId());
                ChunckPayment chunk = new ChunckPayment(currentPayment.getPaymentId(), userBalance.getBalanceId(), chunckAmount);
                userBalance.addChunckPayment(chunk);
                databaseBalanceLogger.updateBalance(userBalance);
                databaseChunkLogger.save(chunk);
            }
        }
    }
    
}
