package GS1.View;

import GS1.Model.PaymentsMethods.PaymentMethod;

public interface PaymentMethodLogger {
    public boolean save(int UserId, PaymentMethod paymentMethod);
}
