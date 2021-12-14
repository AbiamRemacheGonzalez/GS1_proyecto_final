package GS1.App.ManagePayments;

import GS1.Model.Payment;
import GS1.Model.User;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class ManagePaymentsDisplay extends javax.swing.JFrame {

    private ManagePaymentsEvents event;
    
    public ManagePaymentsDisplay() {
        initComponents();
        
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        Title = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        body = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        name = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        amountText = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        paidByCheckbox = new javax.swing.JComboBox<>();
        addPayment = new javax.swing.JButton();
        nameError = new javax.swing.JLabel();
        amountError = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        Title.setBackground(new java.awt.Color(153, 153, 153));
        Title.setLayout(new java.awt.GridBagLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("Add New Payment");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        Title.add(jLabel1, gridBagConstraints);

        body.setBackground(new java.awt.Color(204, 204, 204));
        body.setLayout(new java.awt.GridBagLayout());

        jLabel2.setText("Name");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        body.add(jLabel2, gridBagConstraints);

        name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameActionPerformed(evt);
            }
        });
        name.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nameKeyPressed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        body.add(name, gridBagConstraints);

        jLabel3.setText("Amount");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        body.add(jLabel3, gridBagConstraints);

        amountText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                amountTextKeyPressed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        body.add(amountText, gridBagConstraints);

        jLabel4.setText("Paid by");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        body.add(jLabel4, gridBagConstraints);

        paidByCheckbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paidByCheckboxActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        body.add(paidByCheckbox, gridBagConstraints);

        addPayment.setText("Add Payment");
        addPayment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addPaymentActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        body.add(addPayment, gridBagConstraints);

        nameError.setForeground(new java.awt.Color(255, 0, 0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        body.add(nameError, gridBagConstraints);

        amountError.setForeground(new java.awt.Color(255, 0, 0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        body.add(amountError, gridBagConstraints);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(body, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Title, javax.swing.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Title, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(body, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void paidByCheckboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paidByCheckboxActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_paidByCheckboxActionPerformed

    private void addPaymentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addPaymentActionPerformed
        // TODO add your handling code here:
        this.resetLabels();
        if(amountText.getText().isEmpty()){
            this.printAmountEmpty();
        }else{
            double amount = Double.parseDouble(amountText.getText());
            if(event.checkNewPaymentValues(name.getText(), amount)){
                String userRep = (String) paidByCheckbox.getSelectedItem();
                String parts[] = userRep.split("#");
                User destination = event.getUser(Integer.parseInt(parts[1]));
                Payment payment = new Payment(name.getText(),amount,destination.getId(),event.getIdGroup()); 
                event.savePayment(payment);
                this.dispose();
            }
        }
    }//GEN-LAST:event_addPaymentActionPerformed

    private void nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nameActionPerformed

    private void nameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nameKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_nameKeyPressed

    private void amountTextKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_amountTextKeyPressed
        // TODO add your handling code here:
        String value = amountText.getText();
        if ((evt.getKeyChar() >= '0' && evt.getKeyChar() <= '9') || evt.getKeyChar() == KeyEvent.VK_BACK_SPACE) {
            amountText.setEditable(true);
        } else{
            amountText.setEditable(false);
            amountText.setText(value);
        }
    }//GEN-LAST:event_amountTextKeyPressed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Title;
    private javax.swing.JButton addPayment;
    private javax.swing.JLabel amountError;
    private javax.swing.JTextField amountText;
    private javax.swing.JPanel body;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField name;
    private javax.swing.JLabel nameError;
    private javax.swing.JComboBox<String> paidByCheckbox;
    // End of variables declaration//GEN-END:variables
    
    public void on(ManagePaymentsEvents ev){
        this.event = ev;
    }
    
    public void printErrorTitle(){
        nameError.setText("The title should not be empty");
    }
    
    public void printAmountError(){
        amountError.setText("The amount must to be higher than 0");
    }
    
    public void printAmountEmpty(){
        amountError.setText("The amount should not be empty");
    }
    
    public void resetLabels(){
        nameError.setText("");
        amountError.setText("");
    }
    
    public void setMembersList(){
        ArrayList<User> members = event.getMembers();
        for (User member : members) {
            paidByCheckbox.addItem(member.getFirstname()+"#"+member.getId());
        }
    }

    public interface ManagePaymentsEvents{
       void savePayment(Payment p);
       boolean checkNewPaymentValues(String title, double amount);
       ArrayList<User> getMembers();
       User getUser(int userId);
       int getIdGroup();
    }
}
