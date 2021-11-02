package GS1.Model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreditCardPaymentMethod implements PaymentMethod{
    private final String owner;
    private final int creditNumber;
    private final String expiryDate;
    private final int secretNumber;
    
    private final String creditNumberPattern = "(4[0-9]{12}(?:[0-9]{3})?)|(5[1-5][0-9]{14})";
    private final String secretNumberPattern = "^([0-9]{3})";
    private final String expiryDatePattern = "(([0][1-9]|1[1-2])/([0-2][0-9]|3[0-1]))";

    public CreditCardPaymentMethod(String owner, int creditNumber, String expiryDate, int secretNumber) {
        this.owner = owner;
        this.creditNumber = creditNumber;
        this.expiryDate = expiryDate;
        this.secretNumber = secretNumber;
    }

    public String getOwner() {
        return owner;
    }

    public int getCreditNumber() {
        return creditNumber;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public int getSecretNumber() {
        return secretNumber;
    }
    
    private boolean isCorrectCreditNumber(int creditNumber){
        Pattern pattern = Pattern.compile(creditNumberPattern);
        Matcher matcher = pattern.matcher(Integer.toString(creditNumber));
        return matcher.matches();
    }
    
    private boolean isCorrectExpiryDate(String expiryDate){
        Pattern pattern = Pattern.compile(expiryDatePattern);
        Matcher matcher = pattern.matcher(expiryDate);
        return matcher.matches();
    }
    
    private boolean isCorrectSecretNumber(String secretNumber){
        Pattern pattern = Pattern.compile(secretNumberPattern);
        Matcher matcher = pattern.matcher(secretNumber);
        return matcher.matches();
    }
    
    
    
}
