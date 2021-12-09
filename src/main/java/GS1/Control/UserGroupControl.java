package GS1.Control;

import GS1.App.CreateGroup.AddGroupDisplay;
import GS1.App.CreateGroup.DataBaseGroupLoader;
import GS1.App.CreateGroup.DataBaseGroupLogger;
import GS1.App.Group.AddMemberDisplay;
import GS1.App.Group.DataBaseFriendsLoader;
import GS1.App.Group.EditGroupDisplay;
import GS1.App.Group.GroupDisplay;
import GS1.App.SearchUser.DataBaseUserSearch;
import GS1.App.UserLoginAndSignUp.DataBaseUserLoader;
import GS1.App.UserMainDisplay;
import GS1.App.ManagePayments.ManagePaymentsDisplay;
import GS1.Model.Group;
import GS1.Model.Request;
import GS1.Model.User;
import java.util.ArrayList;

public class UserGroupControl {
    private final UserMainDisplay userMainDisplay;
    private AddGroupDisplay addNewGroupDisplay;
    private GroupDisplay groupDisplay;
    private EditGroupDisplay editGroupDisplay;
    private AddMemberDisplay addMemberDisplay;
    private ManagePaymentsDisplay managePaymentsDisplay;
    
    private final DataBaseGroupLoader dataBaseGroupLoader = new DataBaseGroupLoader();
    private final DataBaseGroupLogger dataBaseGroupLogger = new DataBaseGroupLogger();
    private final DataBaseUserLoader userLoader = new DataBaseUserLoader();
    private final DataBaseUserSearch userSearch = new DataBaseUserSearch();
    private final DataBaseFriendsLoader friendsLoader = new DataBaseFriendsLoader();
    
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
                
            }

            @Override
            public boolean checkNewGroupValues(String name, String description) {
                Boolean res = true;
                addNewGroupDisplay.resetPrintErrors();
                if(name.isEmpty()){
                    res = false;
                    addNewGroupDisplay.printNameError();
                }
                if(description.isEmpty()){
                    res = false;
                    addNewGroupDisplay.printDescriptionError();
                }
                return res;
            }

            @Override
            public void openNewEditGroupDisplay() {
                editGroupDisplay = new EditGroupDisplay();
                editGroupDisplay.on(setOpenEditGroupDisplay());
                editGroupDisplay.updateLabels();
                editGroupDisplay.setVisible(true);
                UserManagePaymentsControl userManagePaymentsControl = new UserManagePaymentsControl(editGroupDisplay,currentUser,currentGroup);
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
        };
    }
    
    private EditGroupDisplay.Events setOpenEditGroupDisplay() {
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
        };
    }
    
    private AddMemberDisplay.Events setEventsNewMemberDisplay() {
        return new AddMemberDisplay.Events(){
            @Override
            public void sendGroupRequest(int userId) {
                Request request = new Request(currentGroup.getIdGroup(), userId, 'G');
                userSearch.sendRequest(request);
            }

            @Override
            public int getUserId(User friend) {
                return currentUser.getId();//userLoader.loadUserId(friend.getMail());
            }
        
        };
    }
}
