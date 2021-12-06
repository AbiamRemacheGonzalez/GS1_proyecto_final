/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GS1.Model;

/**
 *
 * @author hugob
 */
public class Payment {
    
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
