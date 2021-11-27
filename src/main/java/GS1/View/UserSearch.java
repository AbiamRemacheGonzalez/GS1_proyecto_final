package GS1.View;

import GS1.Model.User;

import java.util.ArrayList;

public interface UserSearch {
    public ArrayList<String> search(String search, User currentUser);
    public void sendFriendRequest(int sourceUserId, int destinationUserId);
}
