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
    private User destination;

    public Payment(String title, double amount, User destination) {
        this.title = title;
        this.amount = amount;
        this.destination = destination;
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

    public User getDestination() {
        return destination;
    }

    public void setDestination(User destination) {
        this.destination = destination;
    }
    
    
    
}
