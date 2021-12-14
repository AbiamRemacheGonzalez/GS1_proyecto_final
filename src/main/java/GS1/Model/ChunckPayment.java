package GS1.Model;

public class ChunckPayment {
    private int chunckPaymentid;
    private final int paymentId;
    private final int userId;
    private int chunckAmount;

    public ChunckPayment(int paymentId, int userId, int chunkAmount) {
        this.paymentId = paymentId;
        this.userId = userId;
        this.chunckAmount = chunkAmount;
    }
    
    public int getChunckPaymentid() {
        return chunckPaymentid;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public int getchunckAmount() {
        return chunckAmount;
    }

    public void setChunckPaymentid(int chunckPaymentid) {
        this.chunckPaymentid = chunckPaymentid;
    }

    public void setChunckAmount(int chunckAmount) {
        this.chunckAmount = chunckAmount;
    }
                                         
    public void pay(int amount){
        this.chunckAmount-=amount;
    } 
    public void pay(){
        this.chunckAmount = 0;
    }
}
