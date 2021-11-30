package GS1.App;

import GS1.Model.Group;
import java.util.ArrayList;
import javax.swing.DefaultListModel;

public class UserMainDisplay extends javax.swing.JFrame {

    private PaymentEvents paymentEvent;
    private GroupEvents groupevent;
    private MainEvents mainevent;
    private SearchEvents searchEvent;
    private RequestEvents requestEvent;

    private DefaultListModel groupListModel = new DefaultListModel();

    public UserMainDisplay() {
        initComponents();
        groupList.setModel(groupListModel);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jMenuItem3 = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        groupList = new javax.swing.JList<>();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        signOut = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        searchFriends = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        friendRequests = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        myPaymentsMethods = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        addPaypal = new javax.swing.JMenuItem();
        addBizum = new javax.swing.JMenuItem();
        addCreditCard = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        jMenuItem3.setText("jMenuItem3");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("My Groups");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel1.add(jLabel1, gridBagConstraints);

        groupList.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        groupList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(groupList);

        jMenu1.setText("My Account");

        jMenuItem2.setText("Edit My Profile");
        jMenu1.add(jMenuItem2);
        jMenu1.add(jSeparator2);

        signOut.setText("Log Out");
        signOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signOutActionPerformed(evt);
            }
        });
        jMenu1.add(signOut);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("My Friends");

        searchFriends.setText("Search Friends");
        searchFriends.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchFriendsActionPerformed(evt);
            }
        });
        jMenu2.add(searchFriends);
        jMenu2.add(jSeparator3);

        friendRequests.setText("FriendsRequests");
        friendRequests.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                friendRequestsActionPerformed(evt);
            }
        });
        jMenu2.add(friendRequests);

        jMenuBar1.add(jMenu2);

        jMenu4.setText("My Payments Methods");

        myPaymentsMethods.setText("My Payment Methods");
        myPaymentsMethods.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                myPaymentsMethodsActionPerformed(evt);
            }
        });
        jMenu4.add(myPaymentsMethods);
        jMenu4.add(jSeparator1);

        addPaypal.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.ALT_DOWN_MASK | java.awt.event.InputEvent.CTRL_DOWN_MASK));
        addPaypal.setText("Add Paypal");
        addPaypal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addPaypalActionPerformed(evt);
            }
        });
        jMenu4.add(addPaypal);

        addBizum.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.ALT_DOWN_MASK | java.awt.event.InputEvent.CTRL_DOWN_MASK));
        addBizum.setText("Add Bizum");
        addBizum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBizumActionPerformed(evt);
            }
        });
        jMenu4.add(addBizum);

        addCreditCard.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.ALT_DOWN_MASK | java.awt.event.InputEvent.CTRL_DOWN_MASK));
        addCreditCard.setText("Add Credit Card");
        addCreditCard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addCreditCardActionPerformed(evt);
            }
        });
        jMenu4.add(addCreditCard);

        jMenuBar1.add(jMenu4);

        jMenu3.setText("Manage Groups");

        jMenuItem1.setText("Add New Group");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem1);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 536, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addBizumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBizumActionPerformed
        // TODO add your handling code here:
        paymentEvent.openAddBizumWindow();
        this.dispose();
    }//GEN-LAST:event_addBizumActionPerformed

    private void myPaymentsMethodsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myPaymentsMethodsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_myPaymentsMethodsActionPerformed

    private void addCreditCardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addCreditCardActionPerformed
        // TODO add your handling code here:
        paymentEvent.openAddCreditCardWindow();
        this.dispose();
    }//GEN-LAST:event_addCreditCardActionPerformed

    private void addPaypalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addPaypalActionPerformed
        // TODO add your handling code here:
        paymentEvent.openAddPaypalWindow();
        this.dispose();
    }//GEN-LAST:event_addPaypalActionPerformed

    private void searchFriendsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchFriendsActionPerformed
        // TODO add your handling code here:
        searchEvent.openUserSearchWindow();
    }//GEN-LAST:event_searchFriendsActionPerformed

    private void friendRequestsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_friendRequestsActionPerformed
        // TODO add your handling code here:
        requestEvent.openRequestsWindow();
    }//GEN-LAST:event_friendRequestsActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        groupevent.openAddNewGroupWindow();
        this.dispose();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void signOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signOutActionPerformed
        // TODO add your handling code here:
        mainevent.openLoginDisplay();
        this.dispose();
    }//GEN-LAST:event_signOutActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem addBizum;
    private javax.swing.JMenuItem addCreditCard;
    private javax.swing.JMenuItem addPaypal;
    private javax.swing.JMenuItem friendRequests;
    private javax.swing.JList<String> groupList;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JMenuItem myPaymentsMethods;
    private javax.swing.JMenuItem searchFriends;
    private javax.swing.JMenuItem signOut;
    // End of variables declaration//GEN-END:variables

    public void on(PaymentEvents ev) {
        this.paymentEvent = ev;
    }

    public void on(GroupEvents ev) {
        this.groupevent = ev;
    }

    public void on(MainEvents ev) {
        this.mainevent = ev;
    }

    public void on(SearchEvents ev) {
        this.searchEvent = ev;
    }

    public void on(RequestEvents ev) {
        this.requestEvent = ev;
    }

    private void resetModelList() {
        groupListModel = new DefaultListModel();
        groupList.setModel(groupListModel);
    }

    public void setGroupList() {
        ArrayList<Group> groups = mainevent.getGroups();
        for (Group group : groups) {
            groupListModel.addElement(group.getName()+"#"+group.getIdGroup());
        }
    }

    public interface SearchEvents {

        void openUserSearchWindow();
    }

    public interface RequestEvents {

        void openRequestsWindow();
    }

    public interface PaymentEvents {

        void openAddCreditCardWindow();

        void openAddPaypalWindow();

        void openAddBizumWindow();
    }

    public interface GroupEvents {

        void openAddNewGroupWindow();

        void openEditSelectedGroupWindow();
        //void openAddGroupPayment();
    }

    public interface MainEvents {

        void openLoginDisplay();

        public ArrayList<Group> getGroups(); //COMO COÑO FUNCIONA ESTO (BUENA PREGUNTA)
    }

}
