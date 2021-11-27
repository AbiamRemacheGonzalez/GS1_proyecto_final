package GS1.View;

import java.util.ArrayList;

public interface RequestLoader {
    public ArrayList<String> getResquests(int sourceUserId);
    public void acceptRequest(int sourceUserId,int destinationUserId);
    public void discardRequest(int sourceUserId,int destinationUserId);
}
