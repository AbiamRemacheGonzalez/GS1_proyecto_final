package GS1.Model;

import java.util.ArrayList;

public class Group {
    private int idGroup;
    private String name;
    private String description;
    private int idAdmin;
    private ArrayList<Payment> payments;

    public Group(String name, String description) {
        this.name = name;
        this.description = description;
        payments = new ArrayList<>();
    }
    
    public void addPayment(Payment payment){
        payments.add(payment);
    }
    
    public void removePayment(Payment payment){
        payments.remove(payment);
    }

    public void setIdAdmin(int idAdmin) {
        this.idAdmin = idAdmin;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getIdAdmin() {
        return idAdmin;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(int idGroup) {
        this.idGroup = idGroup;
    }

    @Override
    public String toString() {
        return "Group{" + "name=" + name + ", description=" + description + '}';
    }
    
    
    
}
