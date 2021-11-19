package main.java.GS1.View;

import main.java.GS1.Model.Payments.PaymentMethod;

public interface PaymentMethodLogger {
    public void save(int UserId, PaymentMethod paymentMethod);
}
