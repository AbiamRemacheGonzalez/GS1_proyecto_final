/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GS1.Control;

import GS1.App.Group.EditGroupDisplay;
import GS1.App.UserLoginAndSignUp.DataBaseUserLoader;
import GS1.App.ManagePayments.ManagePaymentsDisplay;
import GS1.App.ManagePayments.DatabasePaymentsLoader;
import GS1.Model.Group;
import GS1.Model.Payment;
import GS1.Model.User;
import java.util.ArrayList;

/**
 *
 * @author hugob
 */
public class UserManagePaymentsControl {
    private ManagePaymentsDisplay managePaymentsDisplay;
    private final DataBaseUserLoader userLoader = new DataBaseUserLoader();
    private DatabasePaymentsLoader paymentLoader = new DatabasePaymentsLoader();
    
    private User currentUser;
    private Group currentGroup;
    private EditGroupDisplay editGroupDisplay;
    
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
                managePaymentsDisplay.initMembersData();
            }
        };
    }
    private ManagePaymentsDisplay.ManagePaymentsEvents setManagePaymentsDisplayEvents(){
        return new ManagePaymentsDisplay.ManagePaymentsEvents(){
            
            @Override
            public int getUserID(User user) {
                return currentUser.getId();//userLoader.loadUserId(user.getMail());
            }

            @Override
            public void savePayment(Payment p) {
                paymentLoader.save(p);
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
            public ArrayList<Integer> getMembersId() {
                return paymentLoader.getMembersId(currentGroup.getIdGroup());
            }

            @Override
            public String getUserbyID(int id) {
                return paymentLoader.getUserByID(id);
            }

            @Override
            public User loadUserByName(String firstname) {
                return paymentLoader.getUserByName(firstname);
            }
        };
    }
    
}
