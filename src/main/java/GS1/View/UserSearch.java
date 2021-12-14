package GS1.View;

import GS1.Model.Request;
import GS1.Model.User;

import java.util.ArrayList;

public interface UserSearch {
    public ArrayList<User> search(String search, User currentUser);
    public boolean sendRequest(Request request);
}
