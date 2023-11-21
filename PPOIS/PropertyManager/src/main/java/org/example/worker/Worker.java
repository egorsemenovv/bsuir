package org.example.worker;

import org.example.database.DatabaseManager;

import java.math.BigDecimal;

public abstract class Worker{
    protected final DatabaseManager db = new DatabaseManager();
    private final int id;
    private final String username;
    private final String password;
    private final java.math.BigDecimal payment;
    private java.math.BigDecimal balance;

    public Worker(int id, String username, String password, BigDecimal payment, BigDecimal balance) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.payment = payment;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public abstract boolean startWork();

    public abstract boolean endWork();

}
