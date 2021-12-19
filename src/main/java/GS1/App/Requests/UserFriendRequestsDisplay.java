package GS1.App.Requests;

import GS1.Model.Group;
import GS1.Model.Request;
import GS1.Model.User;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;

public class UserFriendRequestsDisplay extends javax.swing.JFrame {
    Events event;
    DefaultListModel friendModel;
    private ArrayList<Request> friendRequests;
    
    public UserFriendRequestsDisplay() {
        initComponents();
        friendModel = new DefaultListModel();
        friendRequestList.setModel(friendModel);
        friendRequestList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
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
        declineFriendRequestButton = new javax.swing.JButton();
        acceptFriendRequestButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        Title.setBackground(new java.awt.Color(153, 153, 153));
        Title.setLayout(new java.awt.GridBagLayout());

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("Requests");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        Title.add(jLabel4, gridBagConstraints);

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        friendRequestList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        RequestList.setViewportView(friendRequestList);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel1.add(RequestList, gridBagConstraints);

        declineFriendRequestButton.setText("Decline");
        declineFriendRequestButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                declineFriendRequestButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel1.add(declineFriendRequestButton, gridBagConstraints);

        acceptFriendRequestButton.setText("Accept");
        acceptFriendRequestButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acceptFriendRequestButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel1.add(acceptFriendRequestButton, gridBagConstraints);

        jLabel1.setText("Friend Requests");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 10);
        jPanel1.add(jLabel1, gridBagConstraints);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Title, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Title, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void acceptFriendRequestButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acceptFriendRequestButtonActionPerformed
        event.acceptRequest(friendRequests.get(friendRequestList.getSelectedIndex()));
        JOptionPane.showMessageDialog(this, "The friend request have been accepted");
        setFriendRequests();
    }//GEN-LAST:event_acceptFriendRequestButtonActionPerformed

    private void declineFriendRequestButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_declineFriendRequestButtonActionPerformed
        event.discardRequest(friendRequests.get(friendRequestList.getSelectedIndex()));
        JOptionPane.showMessageDialog(this, "The friend request have been denied");
        setFriendRequests();
    }//GEN-LAST:event_declineFriendRequestButtonActionPerformed
    public void setFriendRequests(){
        friendRequests = event.getRequests('F');
        for (Request request : friendRequests) {
            int userId = request.getSourceId();
            User sourceUser = event.getUser(userId);
            friendModel.addElement(sourceUser.getFirstname()+"#"+userId+" quiere ser tu amigo.");
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane RequestList;
    private javax.swing.JPanel Title;
    private javax.swing.JButton acceptFriendRequestButton;
    private javax.swing.JButton declineFriendRequestButton;
    private javax.swing.JList<String> friendRequestList;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables

    public void on(Events ev) {
        this.event = ev;
    }

    public interface Events {
        public ArrayList<Request> getRequests(char requestType);
        public User getUser(int userId);
        public Group getGroup(int groupId);
        public void acceptRequest(Request request);
        public void discardRequest(Request request);
    }
}
