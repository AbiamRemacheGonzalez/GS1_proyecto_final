package GS1.View;

import GS1.Model.ChunkPayment;
import GS1.Model.Payment;
import java.util.ArrayList;

public interface ChunkPaymentLoader {
    public ArrayList<ChunkPayment> getChunksPayment(int balanceId);
    public Payment getPaymentById(int paymentId);
}
