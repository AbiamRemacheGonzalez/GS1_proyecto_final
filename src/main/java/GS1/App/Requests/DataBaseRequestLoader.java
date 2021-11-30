package GS1.App.Requests;

import GS1.App.UserLoginAndSignUp.DataBaseUserLoader;
import GS1.Model.Request;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import GS1.View.RequestLoader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataBaseRequestLoader implements RequestLoader{
    private Connection cn;
    private Statement st;
    private final DataBaseUserLoader dataBaseUserLoader = new DataBaseUserLoader();
    public DataBaseRequestLoader() {
        try {
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gs1?zeroDateTimeBehavior=CONVERT_TO_NULL","root","");
            st =cn.createStatement();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Not Connected");
        }
    }
    @Override
    public ArrayList<Request> getRequests(int userId, char requestType) {
        ArrayList<Request> requests = new ArrayList<>();
        String sql = "SELECT sourceUserId FROM requests WHERE destinationUserId='" + userId + "' and requestType='"+requestType+"'";
        try {
            st.execute(sql);
            ResultSet r = st.getResultSet();
            while (r.next()) {
                int sourceUserId = Integer.parseInt(r.getString("sourceUserId"));
                requests.add(new Request(sourceUserId,userId,requestType));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseRequestLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return requests;
    }

    @Override
    public void acceptRequest(Request request) {
        char requestType = request.getRequestType();
        if(requestType == 'F') acceptFriendRequest(request);
        if(requestType == 'G') acceptGroupRequest(request);
        discardRequest(request);
    }

    @Override
    public void discardRequest(Request request) {
        try {
            String sql = "DELETE FROM requests WHERE sourceUserId = '"+request.getSourceId()+"' and destinationUserId ='"+request.getUserDestinationId()+"' and requestType = '"+request.getRequestType()+"'";
            st.execute(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseRequestLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void acceptFriendRequest(Request request) {
        try {
            String sql = "INSERT INTO friends(sourceUserId,destinationUserId) VALUES('"+request.getSourceId()+"','"+request.getUserDestinationId()+"')";
            st.execute(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseRequestLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void acceptGroupRequest(Request request) {
        try {
            String sql = "INSERT INTO members(groupId,userId) VALUES('"+request.getSourceId()+"','"+request.getUserDestinationId()+"')";
            st.execute(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseRequestLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
}
