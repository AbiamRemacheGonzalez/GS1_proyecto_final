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
import org.mindrot.jbcrypt.BCrypt;

public class DataBaseUserLoader implements UserLoader{

    private Connection cn;
    private Statement st;
    private String mail="";
    private String pass="";
    private String loadedPassword;
    private User loadUser;
    private final String mailPattern = "^(.+)@(.+)$";
    private Boolean emailPattern=false;
    private Boolean emailExists=false;
    private Boolean passwordIsCorrect=false;
    private static final int LOG_ROUNDS = 12;
    
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
        loadUser = null;
        emailPattern=false;
        emailExists=false;
        passwordIsCorrect=false;
    }
    
    @Override
    public User load() {
        emailPatternCheck();
        emailCheck();
        passwordCheck();
        if(isEmailPatternCorrect()&&emailExists()&&isPasswordCorrect()) return loadUser;
        return null;
    }
    
    public int loadUserId(String email, String password){
        int res = -1;
        try {
            this.mail = email;
            String sql = "SELECT userId FROM users WHERE email='"+email+"' and password='"+getHashedPassword()+"'";
            st.execute(sql);
            ResultSet r = st.getResultSet();
            while(r.next()){
                res = Integer.parseInt(r.getString("userId"));
            }
            passwordIsCorrect = (loadUser==null)?Boolean.FALSE:Boolean.TRUE;

        } catch (SQLException ex) {
            Logger.getLogger(DataBaseUserLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }
    public int loadUserId(String email){
        int res = -1;
        try {
            this.mail = email;
            String sql = "SELECT userId FROM users WHERE email='"+email+"'";
            st.execute(sql);
            ResultSet r = st.getResultSet();
            while(r.next()){
                res = Integer.parseInt(r.getString("userId"));
            }
            passwordIsCorrect = (loadUser==null)?Boolean.FALSE:Boolean.TRUE;

        } catch (SQLException ex) {
            Logger.getLogger(DataBaseUserLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }
    
    public User loadUser(int userId){
        User resUser = null;
        String sql = "SELECT * FROM users WHERE userId='"+userId+"'";
        try {
            st.execute(sql);
            ResultSet r = st.getResultSet();
            while(r.next()){
                resUser = new User(r.getString("firstname"),r.getString("lastname"),r.getString("email"),r.getString("password"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseUserLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return resUser;
    }
    
    private void emailPatternCheck(){
        Pattern pattern = Pattern.compile(mailPattern);
        Matcher matcher = pattern.matcher(mail);
        emailPattern= matcher.matches();
    }
    
    private void emailCheck(){
        try {
            String sql = "SELECT count(*) FROM users WHERE email='"+mail+"'";
            if(st.execute(sql)){
                ResultSet r = st.getResultSet();
                int cuenta=0;
                while(r.next()){
                    cuenta=Integer.parseInt(r.getString("count(*)"));
                }
                emailExists = (cuenta==0)?Boolean.FALSE:Boolean.TRUE;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseUserLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private String getHashedPassword() {
        String pass="";
        try {
            String sql = "SELECT password FROM users WHERE email='"+mail+"'";
            if(st.execute(sql)){
                ResultSet r = st.getResultSet();
                while(r.next()){
                    pass=r.getString("password");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseUserLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pass;
    }
    private void passwordCheck(){
        try {
            passwordIsCorrect = Boolean.FALSE;
            loadedPassword = getHashedPassword();
            if(matches(pass)){
                String sql = "SELECT * FROM users WHERE email='"+mail+"' and password='"+loadedPassword+"'";
                st.execute(sql);
                ResultSet r = st.getResultSet();
                while(r.next()){
                    loadUser = new User(r.getString("firstname"),r.getString("lastname"),r.getString("email"),r.getString("password"));
                }
                passwordIsCorrect = Boolean.TRUE;
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
    public Boolean emailUserExists(String mail){
        this.mail = mail;
        emailCheck();
        return emailExists;
    }
    public Boolean isPasswordCorrect() {
        return passwordIsCorrect;
    }
    private String hash(String plainString) {
        String salt = BCrypt.gensalt(LOG_ROUNDS);
        return BCrypt.hashpw(plainString, salt);
    }
    public boolean matches(String plainString) {
        //return true;
        return BCrypt.checkpw(plainString, loadedPassword);
    }


}
