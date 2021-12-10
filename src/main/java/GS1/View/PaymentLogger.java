package GS1.View;

import GS1.Model.Payment;

public interface PaymentLogger {
    public boolean save(Payment payment);
}
