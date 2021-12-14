package GS1.Model;

public class ChunckPayment {
    private int chunckPaymentid;
    private final int paymentId;
    private final int balanceId;
    private double chunckAmount;

    public ChunckPayment(int paymentId, int balanceId, double chunckAmount) {
        this.paymentId = paymentId;
        this.balanceId = balanceId;
        this.chunckAmount = chunckAmount;
    }

    public int getChunckPaymentid() {
        return chunckPaymentid;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public int getBalanceId() {
        return balanceId;
    }

    public void setChunckAmount(double chunckAmount) {
        this.chunckAmount = chunckAmount;
    }

    public double getChunckAmount() {
        return chunckAmount;
    }
                                 
    public boolean pay(double amount){
        if(amount <= chunckAmount){
            this.chunckAmount-=amount;
            return true;
        }
        return false;
    } 
    public void pay(){
        this.chunckAmount = 0;
    }
}
