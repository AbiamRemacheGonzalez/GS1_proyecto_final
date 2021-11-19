package main.java.GS1.View;

import main.java.GS1.Model.User;

public interface UserLoader {
    void initialize(String mail, String password);
    User load();
}
