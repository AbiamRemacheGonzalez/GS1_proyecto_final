package GS1.Model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User {
    private static int count=0;
    private int userId;
    private String firstname;
    private String lastname;
    private String mail;
    private String password;
    private final String mailPattern = "^(.+)@(.+)$";

    public User(String firstname, String lastname, String mail, String password) {
        this.userId = count++;
        this.firstname = firstname;
        this.lastname = lastname;
        this.mail = mail;
        this.password = password;
    }

    public int getUserId() {
        return userId;
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
    
    
    
    private Boolean isCorrectMail(String mail){
        Pattern pattern = Pattern.compile(mailPattern);
        Matcher matcher = pattern.matcher(mail);
        return matcher.matches();
    }
    
}
