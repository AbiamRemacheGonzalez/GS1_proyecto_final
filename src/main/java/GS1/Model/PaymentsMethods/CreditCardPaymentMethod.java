package GS1.Model.PaymentsMethods;

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
    public boolean pay() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
