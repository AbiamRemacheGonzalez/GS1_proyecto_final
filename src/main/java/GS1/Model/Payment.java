package GS1.Model;

public class Payment {
    private int paymentId;
    private String title;
    private double amount;
    private int userDestinationID;
    private int groupID;

    public Payment(String title, double amount, int userDestinationID, int groupID) {
        this.title = title;
        this.amount = amount;
        this.userDestinationID = userDestinationID;
        this.groupID = groupID;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }
    
    public int getUserDestinationID() {
        return userDestinationID;
    }

    public void setUserDestinationID(int userDestinationID) {
        this.userDestinationID = userDestinationID;
    }

    public int getGroupID() {
        return groupID;
    }

    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
    
    
    
}
