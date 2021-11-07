package GS1.View;

import GS1.Model.User;

public interface UserLoader {
    void initialize(String mail, String password);
    User load();
}
