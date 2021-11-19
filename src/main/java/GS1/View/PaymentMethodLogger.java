package GS1.View;

import GS1.Model.Payments.PaymentMethod;

public interface PaymentMethodLogger {
    public void save(int UserId, PaymentMethod paymentMethod);
}
