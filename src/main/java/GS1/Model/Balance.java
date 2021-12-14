package GS1.Model;

import java.util.ArrayList;

public class Balance {
    private int balanceId;
    private final int memberId;
    private final int groupId;
    private double balance;
    private ArrayList<ChunckPayment> chunckPayments;

    public Balance(int memberId, int groupId) {
        this.memberId = memberId;
        this.groupId = groupId;
        balance = 0;
        chunckPayments = new ArrayList<>();
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }
    
    public int getMemberId() {
        return memberId;
    }

    public int getGroupId() {
        return groupId;
    }

    public int getBalanceId() {
        return balanceId;
    }

    public void setBalanceId(int balanceId) {
        this.balanceId = balanceId;
    }
    
    public void addChunckPayment(ChunckPayment chunckPayment){
        chunckPayments.add(chunckPayment);
        balance += chunckPayment.getChunckAmount();
    }
    
    public void removeChunckPayment(int index){
        if (chunckPayments.get(index).getChunckAmount() == 0){
            chunckPayments.remove(index);
        }
    }
    
    public void payChunckPayment(int index){
        if (chunckPayments.get(index).getChunckAmount() > 0){
            chunckPayments.get(index).pay();
            balance -= chunckPayments.get(index).getChunckAmount();
        }
    }
    
    public void payChunckPayment(int index, double amount){
        if (chunckPayments.get(index).getChunckAmount() >= amount){
            chunckPayments.get(index).pay(amount);
            removeChunckPayment(index);
            balance -= amount;
        }
    }
    
    public void payBalance(){
        int index = 0;
        for (ChunckPayment chunckPayment : chunckPayments) {
            chunckPayment.pay();
            removeChunckPayment(index);
            index++;
        }
        balance = 0;
    }
    
    
}
