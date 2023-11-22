package org.example.user;

import org.example.enums.Building;
import org.example.exceptions.NotEnoughMoneyException;
import org.example.database.DatabaseManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    private static final User user1 = new User(1,
            "Test User",
            "Cyi6fm29flLpHIp86JVMuvgAmAmriPxJ1G6YUCKzFuY=",
            "+375123456789",
            "user@mail.com",
            new BigDecimal("999999999.00"));
    private static final User user2 = new User(2,
            "User Test",
            "Cyi6fm29flLpHIp86JVMuvgAmAmriPxJ1G6YUCKzFuY=",
            "+375987654321",
            "test@mail.com",
            new BigDecimal("0.00"));

    private static final DatabaseManager db = new DatabaseManager();
    @Test
    void addProperty() {
        assertTrue(user1.addProperty(100,
                1,
                "TestAddress",
                "description",
                new BigDecimal("9999999.00"),
                Building.APARTMENT));
    }

    @Test
    void callPlumber() {
        try {
            assertTrue(user1.callPlumber(1));
        } catch (NotEnoughMoneyException e) {
            System.out.println(e.getMessage());
        }
        NotEnoughMoneyException thrown = assertThrows(NotEnoughMoneyException.class ,() ->{
            user2.callPlumber(2);
        });
        assertEquals("Your balance: 0.00\nService price: 100.00", thrown.getMessage());
    }

    @Test
    void callElectrician() {
        try {
            assertTrue(user1.callElectrician(1));
        } catch (NotEnoughMoneyException e) {
            System.out.println(e.getMessage());
        }
        NotEnoughMoneyException thrown = assertThrows(NotEnoughMoneyException.class , () ->{
            user2.callElectrician(2);
        });
        assertEquals("Your balance: 0.00\nService price: 150.00", thrown.getMessage());
    }

    @Test
    void sellProperty() {
        assertTrue(user1.sellProperty(1));
    }

    @Test
    void buyProperty() {
        db.setStatusForProperty(1, 1, true);
        db.setStatusForProperty(2, 2, true);
        try {
            assertTrue(user1.buyProperty(2));
        } catch (NotEnoughMoneyException e) {
            System.out.println(e.getMessage());
        }
        NotEnoughMoneyException thrown = assertThrows(NotEnoughMoneyException.class , () ->{
            user2.buyProperty(1);
        });
        assertEquals("Your balance: 0.00\nProperty price is: 20000000.00", thrown.getMessage());
    }

    @AfterAll
    static void clear(){
        db.deletePropertyFromDatabase("TestAddress");
        db.clearAllRequestedService();
        db.setStatusForProperty(1, 1, false);
        db.setUserIdForProperty(1,1);
        db.setUserIdForProperty(2,2);
        db.updateBalanceForUser(1, new BigDecimal("999999999.00"));
        db.updateBalanceForUser(1, new BigDecimal("0.00"));
    }
}