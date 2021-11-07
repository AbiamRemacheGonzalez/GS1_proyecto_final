package GS1.App.UserLoginAndSignUp;

import GS1.Model.User;
import GS1.View.UserLoader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

public class DataBaseUserLoader implements UserLoader{

    private Connection cn;
    private Statement st;
    private String mail="";
    private String pass="";
    private User loadUser;
    private final String mailPattern = "^(.+)@(.+)$";
    private Boolean emailPattern=false;
    private Boolean emailExists=false;
    private Boolean passwordIsCorrect=false;
    
    public DataBaseUserLoader(){
        try {
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gs1?zeroDateTimeBehavior=CONVERT_TO_NULL","root","");
            st =cn.createStatement();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Not Connected");
        }
    }

    @Override
    public void initialize(String mail, String password) {
        this.mail = mail;
        this.pass = password;
    }
    
    @Override
    public User load() {
        emailPatternCheck();
        emailCheck();
        passwordCheck();
        if(isEmailPatternCorrect()&&emailExists()&&isPasswordCorrect()) return loadUser;
        return null;
    }
    private void emailPatternCheck(){
        Pattern pattern = Pattern.compile(mailPattern);
        Matcher matcher = pattern.matcher(mail);
        emailPattern= matcher.matches();
    }
    
    private void emailCheck(){
        try {
            String sql = "SELECT * FROM users WHERE email='"+mail+"'";
            emailExists = st.execute(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseUserLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void passwordCheck(){
        try {
            if(pass!=""){
                String sql = "SELECT * FROM users WHERE email='"+mail+"' and password='"+pass+"'";
                passwordIsCorrect = st.execute(sql);
                ResultSet r = st.getResultSet();
                while(r.next()){
                loadUser = new User(r.getString("firstname"),r.getString("lastname"),r.getString("email"),r.getString("password"));
                }
            }else{
                passwordIsCorrect = false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseUserLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Boolean isEmailPatternCorrect() {
        return emailPattern;
    }

    public Boolean emailExists() {
        return emailExists;
    }

    public Boolean isPasswordCorrect() {
        return passwordIsCorrect;
    }
    
    
}
