package GS1.Control;

import GS1.App.CreateGroup.AddNewGroup;
import GS1.App.CreateGroup.DataBaseGroupLoader;
import GS1.App.CreateGroup.DataBaseGroupLogger;
import GS1.App.Group.GroupDisplay;
import GS1.App.UserLoginAndSignUp.DataBaseUserLoader;
import GS1.App.UserMainDisplay;
import GS1.Model.Group;
import GS1.Model.User;

public class GroupUserControl {
    private final UserMainDisplay userMainDisplay;
    private AddNewGroup addNewGroup;
    private GroupDisplay groupDisplay;
    
    private final DataBaseGroupLoader dataBaseGroupLoader = new DataBaseGroupLoader();
    private final DataBaseGroupLogger dataBaseGroupLogger = new DataBaseGroupLogger();
    private final DataBaseUserLoader userLoader = new DataBaseUserLoader();
    private final User currentUser;
    private Group currentGroup;

    public GroupUserControl(UserMainDisplay userMainDisplay, User currentUser) {
        this.userMainDisplay = userMainDisplay;
        this.currentUser = currentUser;
        userMainDisplay.on(setUserMainDisplayEvents());
    }

    private UserMainDisplay.GroupEvents setUserMainDisplayEvents() {
        return new UserMainDisplay.GroupEvents() {
            @Override
            public void openAddNewGroupWindow() {
                addNewGroup = new AddNewGroup();
                addNewGroup.on(setAddNewGroupEvents());
                addNewGroup.setVisible(true);
            }

            public void deleteGroup() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            public void openEditSelectedGroupWindow(Group group) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
    }
    
    private AddNewGroup.Events setAddNewGroupEvents() {
        return new AddNewGroup.Events() {
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
            public void openGroupDisplay() {
                groupDisplay = new GroupDisplay();
                groupDisplay.setVisible(true);
            }
        };
    }
    
    
    
    
}
