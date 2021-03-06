package GS1.Model;
import GS1.Model.PaymentsMethods.PaymentMethod;
import java.util.ArrayList;
import org.mindrot.jbcrypt.BCrypt;

public class User {
    
    private static final int LOG_ROUNDS = 12;
    private int id;
    private String firstname;
    private String lastname;
    private String mail;
    private String password;
    private ArrayList<PaymentMethod> payments;
    private ArrayList<Group> groups;
    

    public User(String firstname, String lastname, String mail, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.mail = mail;
        this.password = hash(password);
        payments = new ArrayList<PaymentMethod>();
        groups = new ArrayList<Group>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getMail() {
        return mail;
    }

    public String getPassword() {
        return password;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean matches(String plainString) {
        return BCrypt.checkpw(plainString, password);
    }
    
    private String hash(String plainString) {
        String salt = BCrypt.gensalt(LOG_ROUNDS);
        return BCrypt.hashpw(plainString, salt);
    }
    
    public void addPaymentMethod(PaymentMethod pm){
        payments.add(pm);
    }
    
    public void addGroup(Group group){
        groups.add(group);
    }
    
    public ArrayList getGroups(){
        return groups;
    }
    
}
