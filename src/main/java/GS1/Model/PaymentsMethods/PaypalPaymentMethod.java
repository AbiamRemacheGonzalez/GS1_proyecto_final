package GS1.Model.PaymentsMethods;

public class PaypalPaymentMethod  implements PaymentMethod{
    private final String email;

    public PaypalPaymentMethod(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean pay() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
