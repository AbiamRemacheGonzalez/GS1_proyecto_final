package GS1.App.Requests;

import GS1.Model.Group;
import GS1.Model.Request;
import GS1.Model.User;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;

public class UserGroupRequestsDisplay extends javax.swing.JFrame {
    Events event;
    DefaultListModel groupModel;
    private ArrayList<Request> groupRequests;
    
    public UserGroupRequestsDisplay() {
        initComponents();
        groupModel = new DefaultListModel();
        groupRequestList.setModel(groupModel);
        groupRequestList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        Title = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        panel1 = new java.awt.Panel();
        jScrollPane1 = new javax.swing.JScrollPane();
        groupRequestList = new javax.swing.JList<>();
        jLabel2 = new javax.swing.JLabel();
        acceptGroupRequestButton = new javax.swing.JButton();
        declineGroupRequestButton = new javax.swing.JButton();

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

        panel1.setBackground(new java.awt.Color(204, 204, 204));
        panel1.setLayout(new java.awt.GridBagLayout());

        groupRequestList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(groupRequestList);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        panel1.add(jScrollPane1, gridBagConstraints);

        jLabel2.setText("Group Requests");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 10);
        panel1.add(jLabel2, gridBagConstraints);

        acceptGroupRequestButton.setText("Accept");
        acceptGroupRequestButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acceptGroupRequestButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        panel1.add(acceptGroupRequestButton, gridBagConstraints);

        declineGroupRequestButton.setText("Decline");
        declineGroupRequestButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                declineGroupRequestButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        panel1.add(declineGroupRequestButton, gridBagConstraints);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Title, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)
                    .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Title, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    public void setGroupRequests(){
        groupRequests = event.getRequests('G');
        for (Request request : groupRequests) {
            int groupId = request.getSourceId();
            Group sourceGroup = event.getGroup(groupId);
            User admin = event.getUser(sourceGroup.getIdAdmin());
            groupModel.addElement(admin.getFirstname()+"#"+sourceGroup.getIdAdmin()+" te ha invitado a "+sourceGroup.getName()+"#"+groupId);
        }
    }
    private void acceptGroupRequestButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acceptGroupRequestButtonActionPerformed
        event.acceptRequest(groupRequests.get(groupRequestList.getSelectedIndex()));
        JOptionPane.showMessageDialog(null, "The group request have been accepted");
    }//GEN-LAST:event_acceptGroupRequestButtonActionPerformed

    private void declineGroupRequestButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_declineGroupRequestButtonActionPerformed
        event.discardRequest(groupRequests.get(groupRequestList.getSelectedIndex()));
        JOptionPane.showMessageDialog(null, "The group request have been denied");
    }//GEN-LAST:event_declineGroupRequestButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Title;
    private javax.swing.JButton acceptGroupRequestButton;
    private javax.swing.JButton declineGroupRequestButton;
    private javax.swing.JList<String> groupRequestList;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private java.awt.Panel panel1;
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
