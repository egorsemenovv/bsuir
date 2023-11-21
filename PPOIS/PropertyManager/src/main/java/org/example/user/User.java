package org.example.user;

import org.example.Building;
import org.example.database.DatabaseManager;
import org.example.worker.Electrician;
import org.example.worker.Employee;

import java.math.BigDecimal;

public class User {
    private final DatabaseManager db = new DatabaseManager();
    private int id;
    private String username;
    private String password;
    private String phoneNumber;
    private String email;
    private java.math.BigDecimal balance;

    public User(int id, String username, String password, String phoneNumber, String email, BigDecimal balance) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.balance = balance;
    }
    public boolean addProperty(int square, int floors, String address, String description, java.math.BigDecimal price, Building building){
        return db.addPropertyToDatabase(this.id, square, floors, address, description, price, building);
    }
    public boolean callPlumber(int propertyID){
        if(balance.compareTo(Employee.PLUMBER.getPayment())<0){
            System.out.println("Not enough money");
            return false;
        }
        balance = balance.subtract(Employee.PLUMBER.getPayment());
        db.updateBalanceForUser(this.id, balance);
        return db.addRequestForService(propertyID, Employee.PLUMBER);
    }

    public boolean callElectrician(int propertyId){
        if(balance.compareTo(Employee.ELECTRICIAN.getPayment())<0){
            System.out.println("Not enough money");
            return false;
        }
        balance = balance.subtract(Employee.ELECTRICIAN.getPayment());
        db.updateBalanceForUser(this.id, balance);
        return db.addRequestForService(propertyId, Employee.ELECTRICIAN);
    }
}
