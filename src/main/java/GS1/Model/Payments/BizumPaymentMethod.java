
package GS1.Model.Payments;

public class BizumPaymentMethod implements PaymentMethod{
    private final int phoneNumber;

    public BizumPaymentMethod(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }
    
    @Override
    public void pay() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
