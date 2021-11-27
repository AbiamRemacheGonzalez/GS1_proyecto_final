package GS1.Model;

public class Request {
    private final int sourceId;
    private final int userDestinationId;
    private final char requestType; //F == Friend G == Group

    public Request(int sourceId, int userDestinationId, char requestType) {
        this.sourceId = sourceId;
        this.userDestinationId = userDestinationId;
        this.requestType = requestType;
    }

    public char getRequestType() {
        return requestType;
    }
    
    public int getSourceId() {
        return sourceId;
    }

    public int getUserDestinationId() {
        return userDestinationId;
    }
    
}
