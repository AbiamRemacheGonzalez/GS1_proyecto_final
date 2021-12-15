package GS1.App.Group;

import GS1.Model.Balance;
import GS1.Model.ChunckPayment;
import GS1.Model.Payment;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

public class GroupDisplay extends javax.swing.JFrame {

    private GroupDisplay.Events event;
    DefaultListModel chunksPaymentModel;
    private Balance userBalance;
    private ArrayList<ChunckPayment> chunckList;

    public GroupDisplay() {
        initComponents();
        chunksPaymentModel = new DefaultListModel();
        myPaymentsChuncksList.setModel(chunksPaymentModel);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        labelName = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        myPaymentsChuncksList = new javax.swing.JList<>();
        buttonPay = new javax.swing.JButton();
        backButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        editModeMenu = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        labelName.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        labelName.setText("Name");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel1.add(labelName, gridBagConstraints);

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.setLayout(new java.awt.GridBagLayout());

        myPaymentsChuncksList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                myPaymentsChuncksListMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(myPaymentsChuncksList);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 150;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel2.add(jScrollPane1, gridBagConstraints);

        buttonPay.setText("Pay");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel2.add(buttonPay, gridBagConstraints);

        backButton.setText("Back");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel2.add(backButton, gridBagConstraints);

        jLabel1.setText("Payment Chuncks");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        jPanel2.add(jLabel1, gridBagConstraints);

        jMenu2.setText("Group");

        jMenuItem3.setText("More Details");
        jMenu2.add(jMenuItem3);

        editModeMenu.setText("Edit Mode");
        editModeMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editModeMenuActionPerformed(evt);
            }
        });
        jMenu2.add(editModeMenu);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        // TODO add your handling code here:
        event.openUserMainDisplay();
        this.dispose();
    }//GEN-LAST:event_backButtonActionPerformed

    private void editModeMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editModeMenuActionPerformed
        // TODO add your handling code here:
        if (event.isCurrentUserAdminOfCurrentGroup()) {
            event.openEditGroupDisplayEvents();
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(null, "You are not the group administrator.");
        }
    }//GEN-LAST:event_editModeMenuActionPerformed

    private void myPaymentsChuncksListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_myPaymentsChuncksListMouseClicked
        if (evt.getClickCount() == 2) {
            String chunkSelect = myPaymentsChuncksList.getSelectedValue().substring(0, myPaymentsChuncksList.getSelectedValue().indexOf(":"));
            userBalance = event.getUserBalance();
            chunckList = event.getUserChuncks(userBalance.getBalanceId());
            for (ChunckPayment chunk : chunckList) {
                Payment payment = event.getPaymentById(chunk.getPaymentId());
                if (chunkSelect.equals(payment.getTitle())) {
                    JOptionPane.showMessageDialog(this, "El precio total es: " + payment.getAmount());
                }
            }
        }
    }//GEN-LAST:event_myPaymentsChuncksListMouseClicked

    public void setChunkList() {
        userBalance = event.getUserBalance();
        chunckList = event.getUserChuncks(userBalance.getBalanceId());
        for (ChunckPayment chunk : chunckList) {
            Payment payment = event.getPaymentById(chunk.getPaymentId());
            if (payment != null) {
                chunksPaymentModel.addElement(payment.getTitle() + ": " + chunk.getChunckAmount());
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JButton buttonPay;
    private javax.swing.JMenuItem editModeMenu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelName;
    private javax.swing.JList<String> myPaymentsChuncksList;
    // End of variables declaration//GEN-END:variables

    public void on(Events event) {
        this.event = event;
    }

    public void updateLabels() {
        labelName.setText(event.getGroupName());
    }

    public interface Events {

        void openUserMainDisplay();

        public String getGroupName();

        void openEditGroupDisplayEvents();

        public boolean isCurrentUserAdminOfCurrentGroup();

        public Balance getUserBalance();

        public ArrayList<ChunckPayment> getUserChuncks(int balanceId);

        public Payment getPaymentById(int paymentId);
    }

    public interface chunckPaymentsEvents {
        //public Balance getUserBalance(int userId);
        //public ArrayList<ChunckPayment> getUserChuncks(int userId); 
    }
}
