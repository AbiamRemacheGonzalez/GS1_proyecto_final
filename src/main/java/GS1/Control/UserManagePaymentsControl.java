package GS1.Control;

import GS1.Persistence.Group.DataBaseGroupLoader;
import GS1.App.Group.EditGroupDisplay;
import GS1.App.Group.GroupDisplay;
import GS1.Persistence.Payment.DatabaseBalanceLoader;
import GS1.Persistence.Payment.DatabaseChunkLoader;
import GS1.Persistence.Payment.DatabaseChunkLogger;
import GS1.Persistence.Payment.DatabasePaymentLoader;
import GS1.Persistence.User.DataBaseUserLoader;
import GS1.App.ManagePayments.ManagePaymentsDisplay;
import GS1.Persistence.Payment.DatabasePaymentLogger;
import GS1.App.PaymentGateway.PaymentGatewayDisplay;
import GS1.Persistence.Payment.DatabaseBalanceLogger;
import GS1.Model.Balance;
import GS1.Model.ChunkPayment;
import GS1.Model.Group;
import GS1.Model.Payment;
import GS1.Model.User;
import java.util.ArrayList;
import java.util.List;

public class UserManagePaymentsControl {
    private ManagePaymentsDisplay managePaymentsDisplay;
    private final DataBaseUserLoader databaseUserLoader = new DataBaseUserLoader();
    private DatabasePaymentLogger databasePaymentLogger = new DatabasePaymentLogger();
    private DatabasePaymentLoader databasePaymentLoader = new DatabasePaymentLoader();
    private final DataBaseGroupLoader dataBaseGroupLoader = new DataBaseGroupLoader();
    private final DatabaseBalanceLogger databaseBalanceLogger = new DatabaseBalanceLogger();
    private DatabaseChunkLogger databaseChunkLogger = new DatabaseChunkLogger();
    private DatabaseChunkLoader databaseChunkLoader = new DatabaseChunkLoader();
    private DatabaseBalanceLoader databaseBalanceLoader = new DatabaseBalanceLoader();
    
    private User currentUser;
    private Group currentGroup;
    private EditGroupDisplay editGroupDisplay;
    private ArrayList<User> currentGroupMembers;
    private Payment currentPayment;
    
    public UserManagePaymentsControl(EditGroupDisplay editGroupDisplay,User currentUser,Group currentGroup) {
        this.currentUser = currentUser;
        this.editGroupDisplay = editGroupDisplay;
        this.currentGroup = currentGroup;
        editGroupDisplay.on(setPaymentsEventsDiplayEvents());
    }
    
    private EditGroupDisplay.PaymentsEvents setPaymentsEventsDiplayEvents(){
        return new EditGroupDisplay.PaymentsEvents() {
            @Override
            public void openManagePaymentDisplay() {
                managePaymentsDisplay = new ManagePaymentsDisplay();
                managePaymentsDisplay.on(setManagePaymentsDisplayEvents());
                managePaymentsDisplay.setVisible(true);
                managePaymentsDisplay.setMembersList();
            }
        };
    }
    private ManagePaymentsDisplay.ManagePaymentsEvents setManagePaymentsDisplayEvents(){
        return new ManagePaymentsDisplay.ManagePaymentsEvents(){
            @Override
            public void savePayment(Payment payment) {
                databasePaymentLogger.save(payment);
                payment.setPaymentId(databasePaymentLoader.getPaymentId(payment));
                currentPayment = payment;
                currentGroup.addPayment(payment);
                generateChunckPayments();
            }
            
            @Override
            public boolean checkNewPaymentValues(String title, double amount) {
                managePaymentsDisplay.resetLabels();
                boolean res = true;
                if(title.isEmpty()){
                    res=false;
                    managePaymentsDisplay.printErrorTitle();
                }
                if(amount <= 0){
                    res=false;
                    managePaymentsDisplay.printAmountError();
                }
                return res;
            }

            @Override
            public ArrayList<User> getMembers() {
                currentGroupMembers = dataBaseGroupLoader.getGroupMembers(currentGroup.getIdGroup());
                return currentGroupMembers;
            }

            @Override
            public User getUser(int userId) {
                return databaseUserLoader.loadUser(userId);
            }

            @Override
            public int getIdGroup() {
                return currentGroup.getIdGroup();
            }
        };
    }
                
    private void generateChunckPayments() {
        int userDestinationID = currentPayment.getUserDestinationID();    
        for (User currentGroupMember : currentGroupMembers) {
            if(currentGroupMember.getId()!=userDestinationID){
                ChunkPayment chunk = generateChunk(currentGroupMember);
                if(chunk != null) databaseChunkLogger.save(chunk);
            }
        }
    }

    private ChunkPayment generateChunk(User currentGroupMember) {
        Balance currentGroupMemberBalance = databaseBalanceLoader.getUserBalance(currentGroup.getIdGroup(), currentGroupMember.getId());
        ChunkPayment chunk = null;
        double newChunkAmount = generateChuckAmount(currentGroupMember, currentGroupMemberBalance.getBalanceId());
        if (newChunkAmount != 0.0){
            chunk = new ChunkPayment(currentPayment.getPaymentId(), currentGroupMemberBalance.getBalanceId(), newChunkAmount);
            currentGroupMemberBalance.addChunckPayment(chunk);
            databaseBalanceLogger.updateBalance(currentGroupMemberBalance);
        }
        return chunk;
    }

    private double generateChuckAmount(User currentGroupMember, int currentGroupMemberBalanceid) {
        int userDestinationID = currentPayment.getUserDestinationID();
        Double chunckAmount = 0.0;
        ArrayList<ChunkPayment> chunks = databaseChunkLoader.getUserChunksPaymentsWithAEspecificDestination(currentGroup.getIdGroup(), currentGroupMemberBalanceid, userDestinationID);
        if(!chunks.isEmpty()){
            ChunkPayment selectedChunk = getBestChunk(chunks);
            chunckAmount = adjustChunks(selectedChunk);
        }        
        return chunckAmount;
        
    }

    private ChunkPayment getBestChunk(ArrayList<ChunkPayment> chunks) {
        double max = 0;
        ChunkPayment selected = chunks.get(0);
        for (ChunkPayment chunk : chunks) {
            if(chunk.getChunckAmount()>selected.getChunckAmount())selected = chunk;
        }
        return selected;
    }

    private Double adjustChunks(ChunkPayment selectedChunk) {
        Double chunkAmount = 0.0;
        Double chunckAmountBase = currentPayment.getAmount()/currentGroupMembers.size();
        Double updateChunkAmount = Math.abs(chunckAmountBase - selectedChunk.getChunckAmount());
        
        if(selectedChunk.getChunckAmount() < chunckAmountBase){
            chunkAmount = chunckAmountBase - selectedChunk.getChunckAmount();
            updateChunkAmount = 0.0;
        }
        
        selectedChunk.setChunckAmount(updateChunkAmount);
        
        updateDestinationUserBalance(chunckAmountBase);
        updateDestinationUserChunk(selectedChunk);
        
        return chunkAmount;
    }

    private void updateDestinationUserBalance(Double updateChunkAmount) {
        int userDestinationID = currentPayment.getUserDestinationID();
        Balance destinationUserBalance = databaseBalanceLoader.getUserBalance(currentGroup.getIdGroup(), userDestinationID);
        destinationUserBalance.setBalance(destinationUserBalance.getBalance()-updateChunkAmount);
        databaseBalanceLogger.updateBalance(destinationUserBalance);
    }

    private void updateDestinationUserChunk(ChunkPayment selectedChunk) {
        if(selectedChunk.getChunckAmount()==0.0){
            //DELETE
        }else{
            //UPDATE
        }
    }


    
}
