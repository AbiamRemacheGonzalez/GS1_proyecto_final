package GS1.View;

import GS1.Model.Request;
import java.util.ArrayList;

public interface RequestLoader {
    public ArrayList<Request> getRequests(int userId,char requestType);
    public void acceptRequest(Request request);
    public void discardRequest(Request request);
}
