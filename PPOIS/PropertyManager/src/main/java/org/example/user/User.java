package org.example.user;

import org.example.database.ServiceDatabase;
import org.example.database.UserDatabase;
import org.example.enums.Building;
import org.example.exceptions.NotEnoughMoneyException;
import org.example.database.DatabaseManager;
import org.example.enums.Employee;

import java.math.BigDecimal;

public class User {
    private final UserDatabase userDatabase = new UserDatabase();
    private final ServiceDatabase serviceDatabase = new ServiceDatabase();
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

    /**
     * adds property to database
     *
     * @param square      property`s square
     * @param floors      property`s number of floors
     * @param address     property`s address
     * @param description property`s description
     * @param price       property`s price
     * @param building    property`s type of building
     * @return true if successful, false if not
     */
    public boolean addProperty(int square, int floors, String address, String description, java.math.BigDecimal price, Building building) {
        return userDatabase.addPropertyToDatabase(this.id, square, floors, address, description, price, building);
    }

    /**
     * adding request for a plumber
     *
     * @param propertyID property`s id
     * @return true if successful, false if not
     * @throws NotEnoughMoneyException
     */
    public boolean callPlumber(int propertyID) throws NotEnoughMoneyException {
        if (balance.compareTo(Employee.PLUMBER.getPayment()) < 0) {
            throw new NotEnoughMoneyException("Your balance: " + balance + "\nService price: " + Employee.PLUMBER.getPayment());
        }
        balance = balance.subtract(Employee.PLUMBER.getPayment());
        userDatabase.updateBalanceForUser(this.id, balance);
        serviceDatabase.addRequestForService(propertyID, Employee.PLUMBER);
        return true;
    }

    /**
     * adding request for an electrician
     *
     * @param propertyId property`s id
     * @return true if successful, false if not
     * @throws NotEnoughMoneyException
     */
    public boolean callElectrician(int propertyId) throws NotEnoughMoneyException {
        if (balance.compareTo(Employee.ELECTRICIAN.getPayment()) < 0) {
            throw new NotEnoughMoneyException("Your balance: " + balance + "\nService price: " + Employee.ELECTRICIAN.getPayment());
        }
        balance = balance.subtract(Employee.ELECTRICIAN.getPayment());
        userDatabase.updateBalanceForUser(this.id, balance);
        serviceDatabase.addRequestForService(propertyId, Employee.ELECTRICIAN);
        return true;
    }

    /**
     * sells property
     *
     * @param propertyId property`s id
     * @return true if successful, false if not
     */
    public boolean sellProperty(int propertyId) {
        if (userDatabase.setStatusForProperty(this.id, propertyId, true)) {
            return false;
        }
        java.math.BigDecimal price = userDatabase.getPropertyPrice(propertyId);
        balance = balance.add(price);
        userDatabase.updateBalanceForUser(this.id, balance);
        return true;
    }

    /**
     * buys property
     *
     * @param propertyId property id
     * @return true if successful, false if not
     * @throws NotEnoughMoneyException
     */
    public boolean buyProperty(int propertyId) throws NotEnoughMoneyException {
        java.math.BigDecimal price = userDatabase.getPropertyPrice(propertyId);
        if (balance.compareTo(price) < 0) {
            throw new NotEnoughMoneyException("Your balance: " + balance + "\nProperty price is: " + price);
        } else if (userDatabase.getUserIdFromProperty(propertyId) == this.id) {
            System.out.println("It`s already your property");
            return false;
        }
        balance = balance.subtract(price);
        userDatabase.updateBalanceForUser(this.id, balance);
        userDatabase.setUserIdForProperty(this.id, propertyId);
        userDatabase.setUserIdForProperty(this.id, propertyId);
        userDatabase.setStatusForProperty(this.id, propertyId, false);
        return true;
    }
}
