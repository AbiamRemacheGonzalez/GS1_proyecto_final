package GS1.View;

import GS1.Model.ChunckPayment;
import GS1.Model.Payment;
import java.util.ArrayList;

public interface ChunckPaymentLoader {
    public ArrayList<ChunckPayment> getChunksPayment(int balanceId);
    public Payment getPaymentById(int paymentId);
}
