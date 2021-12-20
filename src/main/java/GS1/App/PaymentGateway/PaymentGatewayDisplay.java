package GS1.App.PaymentGateway;

import GS1.Model.Balance;
import GS1.Model.ChunkPayment;
import GS1.Model.PaymentsMethods.BizumPaymentMethod;
import GS1.Model.PaymentsMethods.CreditCardPaymentMethod;
import GS1.Model.PaymentsMethods.PaymentMethod;
import GS1.Model.PaymentsMethods.PaypalPaymentMethod;
import GS1.Model.User;
import java.util.ArrayList;

public class PaymentGatewayDisplay extends javax.swing.JFrame {
    Events event;
    private ChunkPayment chunk;
    private Balance balance;
    public PaymentGatewayDisplay() {
        initComponents();
        totalAmount.setEditable(false);
        destinationUser.setEditable(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jLabel5 = new javax.swing.JLabel();
        Titulo = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        totalAmount = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        paymentMethods = new javax.swing.JComboBox<>();
        pay = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        destinationUser = new javax.swing.JTextField();

        jLabel5.setText("jLabel5");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        Titulo.setBackground(new java.awt.Color(153, 153, 153));
        Titulo.setLayout(new java.awt.GridBagLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("Payment Gateway");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        Titulo.add(jLabel1, gridBagConstraints);

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Resumen"));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel2.setText("TotalAmount");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel1.add(jLabel2, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel1.add(totalAmount, gridBagConstraints);

        jLabel3.setText("PaymentMethod");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel1.add(jLabel3, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel1.add(paymentMethods, gridBagConstraints);

        pay.setText("Pay");
        pay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                payActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        jPanel1.add(pay, gridBagConstraints);

        jLabel4.setText("Detination User");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        jPanel1.add(jLabel4, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel1.add(destinationUser, gridBagConstraints);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE)
                    .addComponent(Titulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Titulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void payActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_payActionPerformed
        // TODO add your handling code here:
        if(chunk != null) event.payChunck(chunk);
        if(balance!= null) event.payBalance(balance);
        this.dispose();
    }//GEN-LAST:event_payActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Titulo;
    private javax.swing.JTextField destinationUser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton pay;
    private javax.swing.JComboBox<String> paymentMethods;
    private javax.swing.JTextField totalAmount;
    // End of variables declaration//GEN-END:variables
    public void setLabels(){
        if(chunk != null){
            totalAmount.setText(chunk.getChunckAmount()+" €");
            destinationUser.setText(event.getDestinationUser(chunk.getPaymentId()).getFirstname());
        }
        if(balance!= null){
            totalAmount.setText(balance.getBalance()+"");
            destinationUser.setText("Multiple target users");
        }
        
    }
    public void setPaymentMethodCheckBox(){
        ArrayList<PaymentMethod> paymentMethods = event.getUserPaymentMethods();
        for (PaymentMethod paymentMethod : paymentMethods) {
            String representation = getStringRepresentation(paymentMethod);
            this.paymentMethods.addItem(representation);
        }
    }
    public void setBalance(Balance balance){
        this.balance = balance;
        this.chunk = null;
    }
    public void setChunk(ChunkPayment chunk){
        this.chunk = chunk;
        this.balance = null;
    }
    public void on(Events ev){
        this.event = ev;
    }

    private String getStringRepresentation(PaymentMethod paymentMethod) {
        String representation = "";
        if(paymentMethod instanceof BizumPaymentMethod){
            representation = "Bizum: "+((BizumPaymentMethod) paymentMethod).getPhoneNumber();
        }
        if(paymentMethod instanceof CreditCardPaymentMethod){
            representation = "Credit card: "+((CreditCardPaymentMethod) paymentMethod).getCreditNumber();
        }
        if(paymentMethod instanceof PaypalPaymentMethod){
            representation = "Paypal: "+((PaypalPaymentMethod) paymentMethod).getEmail();
        }
        return representation;
    }
    public interface Events{
        public ArrayList<PaymentMethod> getUserPaymentMethods();
        void payChunck(ChunkPayment chunckId);
        void payBalance(Balance balance);
        public User getDestinationUser(int paymentId);
    }


}

