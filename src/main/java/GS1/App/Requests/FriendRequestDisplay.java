package GS1.App.Requests;

import GS1.App.UserLoginAndSignUp.DataBaseUserLoader;
import GS1.Control.UserAccessControl;
import GS1.Model.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

public class FriendRequestDisplay extends javax.swing.JFrame {

    Events event;

    User currentUser;
    ArrayList<String> users;

    DefaultListModel modelo;

    private Connection cn;
    private Statement st;

    public FriendRequestDisplay(User currentUser) {
        initComponents();
        this.currentUser = currentUser;
        modelo = new DefaultListModel();
        friendRequestList.setModel(modelo);
        users = new ArrayList<String>();

        try {
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gs1?zeroDateTimeBehavior=CONVERT_TO_NULL", "root", "");
            st = cn.createStatement();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Not Connected");
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        Title = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        RequestList = new javax.swing.JScrollPane();
        friendRequestList = new javax.swing.JList<>();
        Discard = new javax.swing.JButton();
        Accept = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        Title.setBackground(new java.awt.Color(153, 153, 153));
        Title.setLayout(new java.awt.GridBagLayout());

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("Friend Requests");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        Title.add(jLabel4, gridBagConstraints);

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        friendRequestList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        RequestList.setViewportView(friendRequestList);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel1.add(RequestList, gridBagConstraints);

        Discard.setText("Rechazar");
        Discard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DiscardActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel1.add(Discard, gridBagConstraints);

        Accept.setText("Aceptar");
        Accept.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AcceptActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel1.add(Accept, gridBagConstraints);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(Title, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Title, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 16, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void AcceptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AcceptActionPerformed
        String friend = friendRequestList.getSelectedValue();
        try {
            DataBaseUserLoader userLoader = new DataBaseUserLoader();
            int userId = userLoader.loadUserId(UserAccessControl.loggedUser.getMail(), UserAccessControl.loggedUser.getPassword());
            int friendId = Integer.parseInt(friend.substring(friend.indexOf("#") + 1));
            String sql = "UPDATE friends SET confirmed=TRUE WHERE idUser1='" + friendId + "' AND idUser2='" + userId + "'";
            st.execute(sql);
            JOptionPane.showMessageDialog(rootPane, "Ahora eres amigo de " + friend, "Petición Aceptada", JOptionPane.INFORMATION_MESSAGE);
            modelo.removeElement(friend);
        } catch (SQLException ex) {

            Logger.getLogger(DataBaseUserLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_AcceptActionPerformed

    private void DiscardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DiscardActionPerformed
        String user = friendRequestList.getSelectedValue();
        try {
            DataBaseUserLoader userLoader = new DataBaseUserLoader();
            int userId1 = Integer.parseInt(user.substring(user.indexOf("#") + 1));
            int userId2 = userLoader.loadUserId(UserAccessControl.loggedUser.getMail(), UserAccessControl.loggedUser.getPassword());
            String sql = "DELETE FROM friends WHERE idUser1='" + userId1 + "' AND idUser2='" + userId2 + "'";
            st.execute(sql);
            JOptionPane.showMessageDialog(rootPane, "Se ha rechazado la petición de amistad de " + user, "Petición Rechazada", JOptionPane.INFORMATION_MESSAGE);
            modelo.removeElement(user);
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseUserLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_DiscardActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        try {

            DataBaseUserLoader userLoader = new DataBaseUserLoader();
            int userId = userLoader.loadUserId(UserAccessControl.loggedUser.getMail(), UserAccessControl.loggedUser.getPassword());
            String sql = "SELECT * FROM friends WHERE idUser2='" + userId + "' AND confirmed=FALSE";
            st.execute(sql);
            ResultSet r = st.getResultSet();
            while (r.next()) {
                users.add(r.getString("firstname1") + "#" + r.getString("idUser1"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseUserLoader.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (String name : users) {
            modelo.addElement(name);
        }
    }//GEN-LAST:event_formWindowOpened


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Accept;
    private javax.swing.JButton Discard;
    private javax.swing.JScrollPane RequestList;
    private javax.swing.JPanel Title;
    private javax.swing.JList<String> friendRequestList;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables

    public void on(Events ev) {
        this.event = ev;
    }

    public interface Events {

        ArrayList<String> showFriendResquests();

        public void addFriend(String friend);

        public void discardFriend(String user);
    }
}
