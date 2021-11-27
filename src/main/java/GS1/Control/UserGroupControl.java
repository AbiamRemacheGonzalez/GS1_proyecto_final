package GS1.Control;

import GS1.App.CreateGroup.AddGroupDisplay;
import GS1.App.CreateGroup.DataBaseGroupLoader;
import GS1.App.CreateGroup.DataBaseGroupLogger;
import GS1.App.Group.AddNewMember;
import GS1.App.Group.DataBaseFriendsLoader;
import GS1.App.Group.EditGroupDisplay;
import GS1.App.Group.GroupDisplay;
import GS1.App.UserLoginAndSignUp.DataBaseUserLoader;
import GS1.App.UserLoginAndSignUp.UserLoginDisplay;
import GS1.App.UserMainDisplay;
import GS1.Model.Group;
import GS1.Model.User;
import java.util.ArrayList;

public class UserGroupControl {
    private final UserMainDisplay userMainDisplay;
    private AddGroupDisplay addNewGroupDisplay;
    private GroupDisplay groupDisplay;
    private EditGroupDisplay editGroupDisplay;
    private AddNewMember addNewMember;
    
    private final DataBaseGroupLoader dataBaseGroupLoader = new DataBaseGroupLoader();
    private final DataBaseGroupLogger dataBaseGroupLogger = new DataBaseGroupLogger();
    private final DataBaseUserLoader userLoader = new DataBaseUserLoader();
    private final DataBaseFriendsLoader friendsLoader = new DataBaseFriendsLoader();
    
    private final User currentUser;
    private Group currentGroup;

    public UserGroupControl(UserMainDisplay userMainDisplay, User currentUser) {
        this.userMainDisplay = userMainDisplay;
        this.currentUser = currentUser;
        userMainDisplay.on(setUserMainDisplayEvents());
    }

    private UserMainDisplay.GroupEvents setUserMainDisplayEvents() {
        return new UserMainDisplay.GroupEvents() {
            @Override
            public void openAddNewGroupWindow() {
                addNewGroup = new AddGroupDisplay();
                addNewGroup.on(setAddNewGroupEvents());
                addNewGroup.setVisible(true);
            }

            @Override
            public void openEditSelectedGroupWindow() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
                int userId = userLoader.loadUserId(currentUser.getMail(), currentUser.getPassword());
                dataBaseGroupLogger.save(userId, group);
                group.setIdGroup(dataBaseGroupLogger.getGroupId(userId, group.getName(), group.getDescription()));
                currentUser.addGroup(group);
                userMainDisplay.setUser(currentUser);
            }

            @Override
            public boolean checkNewGroupValues(String name, String description) {
                Boolean res = true;
                addNewGroup.resetPrintErrors();
                if(name.isEmpty()){
                    res = false;
                    addNewGroup.printNameError();
                }
                if(description.isEmpty()){
                    res = false;
                    addNewGroup.printDescriptionError();
                }
                return res;
            }

            @Override
            public void openNewEditGroupDisplay() {
                editGroupDisplay = new EditGroupDisplay();
                editGroupDisplay.on(setOpenEditGroupDisplay());
                editGroupDisplay.updateLabels();
                editGroupDisplay.setVisible(true);
            }
        };
    }
    private EditGroupDisplay.Events setOpenEditGroupDisplay() {
        return new EditGroupDisplay.Events() {
            @Override
            public void openUserMainDisplay() {
                userMainDisplay.setVisible(true);
            }

            @Override
            public String getGroupName() {
                return currentGroup.getName();
            }

            @Override
            public void openAddNewMemberDisplay() {
                addNewMember = new AddNewMember();
                addNewMember.on(setEventsNewMemberDisplay());
                ArrayList<String> friendList = friendsLoader.search("", currentUser);
                addNewMember.setFriendsList(friendList);
                addNewMember.setVisible(true);
            }
        };
    }
    private AddNewMember.Events setEventsNewMemberDisplay() {
        return new AddNewMember.Events(){
            @Override
            public void sendGroupRequest() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        
        };
    }
}
