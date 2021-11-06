package GS1.View;

import GS1.Model.User;

public interface UserLoader {
    User load(String mail, String password);
}
