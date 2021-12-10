package GS1.Model;

import java.util.ArrayList;

public class Balance {
    private final int memberId;
    private final int groupId;
    private int balance;
    private ArrayList<ChunckPayment> chunckPayments;

    public Balance(int memberId, int groupId) {
        this.memberId = memberId;
        this.groupId = groupId;
        balance = 0;
        chunckPayments = new ArrayList<>();
    }
    
    public void addChunckPayment(ChunckPayment chunckPayment){
        chunckPayments.add(chunckPayment);
        balance += chunckPayment.getchunckAmount();
    }
    
    public void removeChunckPayment(int index){
        if (chunckPayments.get(index).getchunckAmount() == 0){
            chunckPayments.remove(index);
        }
    }
    
    public void payChunckPayment(int index){
        if (chunckPayments.get(index).getchunckAmount() > 0){
            chunckPayments.get(index).pay();
            balance -= chunckPayments.get(index).getchunckAmount();
        }
    }
    
    public void payChunckPayment(int index, int amount){
        if (chunckPayments.get(index).getchunckAmount() >= amount){
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
