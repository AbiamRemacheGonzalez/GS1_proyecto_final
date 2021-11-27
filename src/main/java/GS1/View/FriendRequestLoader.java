package GS1.View;

import java.util.ArrayList;

public interface FriendRequestLoader {
    public ArrayList<String> getFriendsResquests(int sourceUserId);
    public void acceptRequest(int sourceUserId,int destinationUserId);
    public void discardRequest(int sourceUserId,int destinationUserId);
}
