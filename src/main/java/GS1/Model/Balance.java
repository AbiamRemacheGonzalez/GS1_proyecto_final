package GS1.Model;

import java.util.ArrayList;

public class Balance {
    private int balanceId;
    private final int memberId;
    private final int groupId;
    private double balance;
    private ArrayList<ChunkPayment> chunkPayments;

    public Balance(int memberId, int groupId) {
        this.memberId = memberId;
        this.groupId = groupId;
        balance = 0;
        chunkPayments = new ArrayList<>();
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
    
    public void addChunckPayment(ChunkPayment chunckPayment){
        chunkPayments.add(chunckPayment);
        balance += chunckPayment.getChunckAmount();
    }
    
    public void removeChunckPayment(int index){
        if (chunkPayments.get(index).getChunckAmount() == 0){
            chunkPayments.remove(index);
        }
    }
    
    public void payChunckPayment(int index){
        if (chunkPayments.get(index).getChunckAmount() > 0){
            chunkPayments.get(index).pay();
            balance -= chunkPayments.get(index).getChunckAmount();
        }
    }
    
    public void payChunckPayment(int index, double amount){
        if (chunkPayments.get(index).getChunckAmount() >= amount){
            chunkPayments.get(index).pay(amount);
            removeChunckPayment(index);
            balance -= amount;
        }
    }
    
    public void payBalance(){
        int index = 0;
        for (ChunkPayment chunckPayment : chunkPayments) {
            chunckPayment.pay();
            removeChunckPayment(index);
            index++;
        }
        balance = 0;
    }
    
    
}
