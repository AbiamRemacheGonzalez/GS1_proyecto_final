package GS1.Control;

import GS1.Persistence.PaymentMethod.DataBasePaymentMethodLoader;
import GS1.App.CreateGroup.AddGroupDisplay;
import GS1.Persistence.Group.DataBaseGroupLoader;
import GS1.Persistence.Group.DataBaseGroupLogger;
import GS1.App.Group.AddMemberDisplay;
import GS1.Persistence.User.DataBaseFriendsLoader;
import GS1.App.Group.EditGroupDisplay;
import GS1.App.Group.GroupDisplay;
import GS1.Persistence.Payment.DatabasePaymentLogger;
import GS1.Persistence.User.DataBaseUserSearch;
import GS1.Persistence.User.DataBaseUserLoader;
import GS1.App.UserMainDisplay;
import GS1.App.ManagePayments.ManagePaymentsDisplay;
import GS1.Persistence.Payment.DatabaseBalanceLogger;
import GS1.Persistence.Payment.DatabaseBalanceLoader;
import GS1.Persistence.Payment.DatabaseChunkLoader;
import GS1.App.PaymentGateway.PaymentGatewayDisplay;
import GS1.Model.Balance;
import GS1.Model.ChunkPayment;
import GS1.Model.Group;
import GS1.Model.Payment;
import GS1.Model.Request;
import GS1.Model.User;
import java.util.ArrayList;
import java.util.List;

public class UserGroupControl {

    private final UserMainDisplay userMainDisplay;
    private AddGroupDisplay addNewGroupDisplay;
    private GroupDisplay groupDisplay;
    private EditGroupDisplay editGroupDisplay;
    private AddMemberDisplay addMemberDisplay;
    private ManagePaymentsDisplay managePaymentsDisplay;
    private PaymentGatewayDisplay paymentGatewayDisplay;

    private final DataBaseGroupLoader dataBaseGroupLoader = new DataBaseGroupLoader();
    private final DataBaseGroupLogger dataBaseGroupLogger = new DataBaseGroupLogger();
    private final DataBaseUserLoader userLoader = new DataBaseUserLoader();
    private final DataBaseUserSearch userSearch = new DataBaseUserSearch();
    private final DataBaseFriendsLoader friendsLoader = new DataBaseFriendsLoader();
    private final DatabasePaymentLogger databasePaymentLoader = new DatabasePaymentLogger();
    private final DatabaseBalanceLogger dataBaseUserBalanceLogger = new DatabaseBalanceLogger();
    private final DatabaseBalanceLoader dataBaseUserBalanceLoader = new DatabaseBalanceLoader();
    private final DatabaseChunkLoader dataBaseChunkPaymentLoader = new DatabaseChunkLoader();
    private final DataBasePaymentMethodLoader databasePaymentMethodLoader = new DataBasePaymentMethodLoader();

    private final User currentUser;
    private Group currentGroup;

    public UserGroupControl(UserMainDisplay userMainDisplay, User currentUser) {
        this.userMainDisplay = userMainDisplay;
        this.currentUser = currentUser;
        userMainDisplay.on(setUserMainDisplayGroupEvents());
    }

    private UserMainDisplay.GroupEvents setUserMainDisplayGroupEvents() {
        return new UserMainDisplay.GroupEvents() {
            @Override
            public void openAddNewGroupWindow() {
                addNewGroupDisplay = new AddGroupDisplay();
                addNewGroupDisplay.on(setAddNewGroupEvents());
                addNewGroupDisplay.setVisible(true);
            }

            @Override
            public void openSelectedGroupWindow(Group g) {
                currentGroup = g;
                groupDisplay = new GroupDisplay();
                groupDisplay.on(setOpenGroupDisplay());
                groupDisplay.setChunkList();
                groupDisplay.updateLabels();
                groupDisplay.setVisible(true);
            }
        };
    }

    private AddGroupDisplay.Events setAddNewGroupEvents() {
        return new AddGroupDisplay.Events() {
            @Override
            public void openUserMainDisplay() {
                userMainDisplay.setVisible(true);
            }

            @Override
            public void saveNewGroup(Group group) {
                currentGroup = group;
                //int userId = userLoader.loadUserId(currentUser.getMail(), currentUser.getPassword());
                dataBaseGroupLogger.save(currentUser.getId(), group);
                group.setIdGroup(dataBaseGroupLogger.getGroupId(currentUser.getId(), group.getName(), group.getDescription()));
                currentUser.addGroup(group);
                Balance balance = new Balance(currentUser.getId(), currentGroup.getIdGroup());
                dataBaseUserBalanceLogger.save(balance);
                userMainDisplay.setGroupList();
            }

            @Override
            public boolean checkNewGroupValues(String name, String description) {
                Boolean res = true;
                addNewGroupDisplay.resetPrintErrors();
                if (name.isEmpty()) {
                    res = false;
                    addNewGroupDisplay.printNameError();
                }
                if (description.isEmpty()) {
                    res = false;
                    addNewGroupDisplay.printDescriptionError();
                }
                return res;
            }

            @Override
            public void openNewEditGroupDisplay() {
                editGroupDisplay = new EditGroupDisplay();
                editGroupDisplay.on(setOpenEditGroupDisplayEvents());
                editGroupDisplay.updateLabels();
                editGroupDisplay.setVisible(true);
                UserManagePaymentsControl userManagePaymentsControl = new UserManagePaymentsControl(editGroupDisplay, currentUser, currentGroup);
                editGroupDisplay.setMembersList();
            }
        };
    }

    private GroupDisplay.Events setOpenGroupDisplay() {
        return new GroupDisplay.Events() {
            @Override
            public void openUserMainDisplay() {
                userMainDisplay.setGroupList();
                userMainDisplay.setVisible(true);
            }

            @Override
            public String getGroupName() {
                return currentGroup.getName();
            }

            @Override
            public void openEditGroupDisplayEvents() {
                editGroupDisplay = new EditGroupDisplay();
                editGroupDisplay.on(setOpenEditGroupDisplayEvents());
                editGroupDisplay.updateLabels();
                editGroupDisplay.setVisible(true);
                UserManagePaymentsControl userManagePaymentsControl = new UserManagePaymentsControl(editGroupDisplay, currentUser, currentGroup);
                editGroupDisplay.setMembersList();
            }

            @Override
            public boolean isCurrentUserAdminOfCurrentGroup() {
                return dataBaseGroupLogger.isAdminOfCurrentGroup(currentUser.getId(), currentGroup.getIdGroup());
            }

            @Override
            public Balance getUserBalance() {
                return dataBaseUserBalanceLoader.getUserBalance(currentGroup.getIdGroup(), currentUser.getId());
            }

            @Override
            public ArrayList<ChunkPayment> getUserChuncks(int balanceId) {
                return dataBaseChunkPaymentLoader.getChunksPayment(balanceId);
            }
            
            @Override
            public Payment getPaymentById(int paymentId) {
                return dataBaseChunkPaymentLoader.getPaymentById(paymentId);
            }

            @Override
            public boolean payChunck(ChunkPayment chunckId) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public boolean payBalance(Balance balance) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
    }
    private PaymentGatewayDisplay.Events setPaymentGatewayDisplayEvents(){
        return new PaymentGatewayDisplay.Events(){
            @Override
            public float getImportChunkPayments(List<String> chunkPayments) {
                float res=0;
                for(String chunck: chunkPayments){
                    String aux = chunck.substring(chunck.indexOf(':')+1,chunck.length());
                    res += Float.parseFloat(aux);
                }
                return res;
            }
            
        };
    }       
    
    private EditGroupDisplay.Events setOpenEditGroupDisplayEvents() {
        return new EditGroupDisplay.Events() {
            @Override
            public void openUserMainDisplay() {
                userMainDisplay.setGroupList();
                userMainDisplay.setVisible(true);
            }

            @Override
            public String getGroupName() {
                return currentGroup.getName();
            }

            @Override
            public void openAddNewMemberDisplay() {
                addMemberDisplay = new AddMemberDisplay();
                addMemberDisplay.on(setEventsNewMemberDisplay());
                ArrayList<User> friendList = friendsLoader.search("", currentUser);
                addMemberDisplay.setFriendsList(friendList);
                addMemberDisplay.setVisible(true);
            }

            @Override
            public ArrayList<User> getMembers() {
                return dataBaseGroupLoader.getGroupMembers(currentGroup.getIdGroup());
            }
        };
    }

    private AddMemberDisplay.Events setEventsNewMemberDisplay() {
        return new AddMemberDisplay.Events() {
            @Override
            public boolean sendGroupRequest(int userId) {
                Request request = new Request(currentGroup.getIdGroup(), userId, 'G');
                return userSearch.sendRequest(request);
            }

            @Override
            public int getUserId(User friend) {
                return currentUser.getId();//userLoader.loadUserId(friend.getMail());
            }

        };
    }
}
