package GS1.View;

import java.util.ArrayList;

public interface FriendRequestLoader {
    public ArrayList<String> showFriendResquests();
    public void addFriend(String friend);
    public void discardFriend(String user);
}
