package GS1.View;

import GS1.Model.Payment;
import java.util.ArrayList;

public interface PaymentLoader {
    public ArrayList<Payment> getGroupsPayments(int groupId);
}
