/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GS1.App.AddNewPaymentMethod;
import GS1.Model.Payments.CreditCardPaymentMethod;
import GS1.Model.User;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author Exulonk
 */
public class NewCreditCardPaymentDisplay extends javax.swing.JFrame {
    private Events event;
    private User newUser;
    private final int MONTHSIZE = 2;
    private final int YEARSIZE = 2;
    private final int CREDITNUMBERSIZE = 16;
    public NewCreditCardPaymentDisplay() {
        initComponents();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        creditNumberField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        expiryDateMonthField = new javax.swing.JTextField();
        expiryDateYearField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        ownerField = new javax.swing.JTextField();
        ownerError = new javax.swing.JLabel();
        creditNumberError = new javax.swing.JLabel();
        expiryDateError = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        cancelButton = new javax.swing.JButton();
        finishButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(189, 249, 189));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("New Credit Card");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel1.add(jLabel1, gridBagConstraints);

        jPanel2.setBackground(new java.awt.Color(204, 255, 204));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel2.setLayout(new java.awt.GridBagLayout());

        jLabel2.setText("Credit Number");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 20);
        jPanel2.add(jLabel2, gridBagConstraints);

        creditNumberField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                creditNumberFieldKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                creditNumberFieldKeyTyped(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 150;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel2.add(creditNumberField, gridBagConstraints);

        jLabel3.setText("Expiry Date");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 20);
        jPanel2.add(jLabel3, gridBagConstraints);

        expiryDateMonthField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                expiryDateMonthFieldKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                expiryDateMonthFieldKeyTyped(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 20;
        gridBagConstraints.insets = new java.awt.Insets(10, 5, 10, 10);
        jPanel2.add(expiryDateMonthField, gridBagConstraints);

        expiryDateYearField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                expiryDateYearFieldKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                expiryDateYearFieldKeyTyped(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 5);
        jPanel2.add(expiryDateYearField, gridBagConstraints);

        jLabel4.setText("Owner");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 20);
        jPanel2.add(jLabel4, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 150;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel2.add(ownerField, gridBagConstraints);

        ownerError.setForeground(new java.awt.Color(255, 51, 51));
        jPanel2.add(ownerError, new java.awt.GridBagConstraints());

        creditNumberError.setForeground(new java.awt.Color(255, 51, 51));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 1;
        jPanel2.add(creditNumberError, gridBagConstraints);

        expiryDateError.setForeground(new java.awt.Color(255, 51, 51));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 2;
        jPanel2.add(expiryDateError, gridBagConstraints);

        jPanel3.setBackground(new java.awt.Color(204, 255, 204));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel3.setLayout(new java.awt.GridBagLayout());

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel3.add(cancelButton, gridBagConstraints);

        finishButton.setText("Finish");
        finishButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                finishButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel3.add(finishButton, gridBagConstraints);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void finishButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_finishButtonActionPerformed
        // TODO add your handling code here:
        int month = 0;
        int year =0;
        long creditNumber = 0;
        if(!expiryDateMonthField.getText().isEmpty() && !expiryDateYearField.getText().isEmpty()){
            month = Integer.parseInt(expiryDateMonthField.getText())-1;
            year = Integer.parseInt(expiryDateYearField.getText())+2000;
        }
        if(!creditNumberField.getText().isEmpty()){
            creditNumber = Long.parseLong(creditNumberField.getText());
        }
        Calendar myCalendar = new GregorianCalendar(year, month, 1);
        Date myDate = myCalendar.getTime();
        if(event.checkCreditValues(ownerField.getText(), creditNumber, myDate)){
            CreditCardPaymentMethod credit = new CreditCardPaymentMethod(ownerField.getText(),Long.parseLong(creditNumberField.getText()),myDate);
            event.signUp(newUser, credit);
            this.dispose();
        }
        
    }//GEN-LAST:event_finishButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        // TODO add your handling code here:
        event.openUserRegistrationWindow();
        this.dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void expiryDateYearFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_expiryDateYearFieldKeyTyped
        // TODO add your handling code here:
        if (expiryDateYearField.getText().length()== YEARSIZE){
            evt.consume();
        }
    }//GEN-LAST:event_expiryDateYearFieldKeyTyped

    private void expiryDateMonthFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_expiryDateMonthFieldKeyTyped
        // TODO add your handling code here:
        if (expiryDateMonthField.getText().length()== MONTHSIZE){
            evt.consume();
        }
    }//GEN-LAST:event_expiryDateMonthFieldKeyTyped

    private void creditNumberFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_creditNumberFieldKeyTyped
        // TODO add your handling code here:
        String credit = creditNumberField.getText();
        if (creditNumberField.getText().length() == CREDITNUMBERSIZE){
            evt.consume();
        }
    }//GEN-LAST:event_creditNumberFieldKeyTyped

    private void creditNumberFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_creditNumberFieldKeyPressed
        String value = creditNumberField.getText();
        if ((evt.getKeyChar() >= '0' && evt.getKeyChar() <= '9') || evt.getKeyChar() == KeyEvent.VK_BACK_SPACE) {
            creditNumberField.setEditable(true);
        } else{
            creditNumberField.setEditable(false);
            creditNumberField.setText(value);
        }
    }//GEN-LAST:event_creditNumberFieldKeyPressed

    private void expiryDateYearFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_expiryDateYearFieldKeyPressed
        String value = expiryDateYearField.getText();
        if ((evt.getKeyChar() >= '0' && evt.getKeyChar() <= '9') || evt.getKeyChar() == KeyEvent.VK_BACK_SPACE) {
            expiryDateYearField.setEditable(true);
        } else {
            expiryDateYearField.setEditable(false);
            expiryDateYearField.setText(value);
        }
    }//GEN-LAST:event_expiryDateYearFieldKeyPressed

    private void expiryDateMonthFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_expiryDateMonthFieldKeyPressed
        // TODO add your handling code here:
        String value = expiryDateMonthField.getText();
        if ((evt.getKeyChar() >= '0' && evt.getKeyChar() <= '9') || evt.getKeyChar() == KeyEvent.VK_BACK_SPACE)  {
            expiryDateMonthField.setEditable(true);
        } else {
            expiryDateMonthField.setEditable(false);
            expiryDateMonthField.setText(value);
        }
    }//GEN-LAST:event_expiryDateMonthFieldKeyPressed
public void resetErrorPrints(){
        ownerError.setText("");
        creditNumberError.setText("");
        expiryDateError.setText("");
    }
    public void printOwnerError(){
        ownerError.setText("The owner should not be empty.");
    }
    public void printCreditNumberError(){
        creditNumberError.setText("The credit card pattern is not correct.");
    }
    public void printExpiryDateError(){
        expiryDateError.setText("The date is incorrect.");
    }
    public void printCreditExpiredError(){
        expiryDateError.setText("The credit card is expired.");
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JLabel creditNumberError;
    private javax.swing.JTextField creditNumberField;
    private javax.swing.JLabel expiryDateError;
    private javax.swing.JTextField expiryDateMonthField;
    private javax.swing.JTextField expiryDateYearField;
    private javax.swing.JButton finishButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel ownerError;
    private javax.swing.JTextField ownerField;
    // End of variables declaration//GEN-END:variables
    public void setUser(User user){
        this.newUser = user;
    }
    public void on(Events ev) {
        this.event = ev;
    }
    public interface Events{
        void signUp(User user, CreditCardPaymentMethod credit);
        Boolean checkCreditValues(String owner, long creditNumber,Date expiryDate);
        void openUserRegistrationWindow();
    }
}
