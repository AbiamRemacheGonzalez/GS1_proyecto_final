package GS1.Model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User {
    private String name;
    private String surname;
    private String lastname;
    private String mail;
    private final String mailPattern = "^(.+)@(.+)$";

    public User(String name, String surname, String lastname, String mail) {
        this.name = name;
        this.surname = surname;
        this.lastname = lastname;
        setMail(mail);
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getMail() {
        return mail;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
    
    private Boolean isCorrectMail(String mail){
        Pattern pattern = Pattern.compile(mailPattern);
        Matcher matcher = pattern.matcher(mail);
        return matcher.matches();
    }
    
}
