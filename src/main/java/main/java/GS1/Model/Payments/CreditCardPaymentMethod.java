package main.java.GS1.Model.Payments;

import java.util.Date;

public class CreditCardPaymentMethod implements PaymentMethod{
    private final String owner;
    private final Long creditNumber;
    private final Date expiryDate;

    public CreditCardPaymentMethod(String owner, Long creditNumber, Date expiryDate) {
        this.owner = owner;
        this.creditNumber = creditNumber;
        this.expiryDate = expiryDate;
    }

    public String getOwner() {
        return owner;
    }

    public Long getCreditNumber() {
        return creditNumber;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    @Override
    public void pay() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
