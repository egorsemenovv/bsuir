package org.example.propertyowner;

import org.example.database.ServiceDatabase;
import org.example.database.PropertyOwnerDatabase;
import org.example.enums.Building;
import org.example.exceptions.NotEnoughMoneyException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

class PropertyOwnerTest {
    private static final PropertyOwner propertyOwner1 = new PropertyOwner(1,
            "Test User",
            "Cyi6fm29flLpHIp86JVMuvgAmAmriPxJ1G6YUCKzFuY=",
            "+375123456789",
            "user@mail.com",
            new BigDecimal("999999999.00"));
    private static final PropertyOwner propertyOwner2 = new PropertyOwner(2,
            "User Test",
            "Cyi6fm29flLpHIp86JVMuvgAmAmriPxJ1G6YUCKzFuY=",
            "+375987654321",
            "test@mail.com",
            new BigDecimal("0.00"));

    private static final PropertyOwnerDatabase propertyOwnerDatabase = new PropertyOwnerDatabase();
    private static final ServiceDatabase serviceDatabase = new ServiceDatabase();
    @Test
    void addProperty() {
        assertTrue(propertyOwner1.addProperty(100,
                1,
                "TestAddress",
                "description",
                new BigDecimal("9999999.00"),
                Building.APARTMENT));
    }

    @Test
    void callPlumber() {
        try {
            assertTrue(propertyOwner1.callPlumber(1));
        } catch (NotEnoughMoneyException e) {
            System.out.println(e.getMessage());
        }
        NotEnoughMoneyException thrown = assertThrows(NotEnoughMoneyException.class ,() ->{
            propertyOwner2.callPlumber(2);
        });
        assertEquals("Your balance: 0.00\nService price: 100.00", thrown.getMessage());
    }

    @Test
    void callElectrician() {
        try {
            assertTrue(propertyOwner1.callElectrician(1));
        } catch (NotEnoughMoneyException e) {
            System.out.println(e.getMessage());
        }
        NotEnoughMoneyException thrown = assertThrows(NotEnoughMoneyException.class , () ->{
            propertyOwner2.callElectrician(2);
        });
        assertEquals("Your balance: 0.00\nService price: 150.00", thrown.getMessage());
    }

    @Test
    void sellProperty() {
        assertTrue(propertyOwner1.sellProperty(1));
    }

    @Test
    void buyProperty() {
        propertyOwnerDatabase.setStatusForProperty(1, 1, true);
        propertyOwnerDatabase.setStatusForProperty(2, 2, true);
        try {
            assertTrue(propertyOwner1.buyProperty(2));
        } catch (NotEnoughMoneyException e) {
            System.out.println(e.getMessage());
        }
        NotEnoughMoneyException thrown = assertThrows(NotEnoughMoneyException.class , () ->{
            propertyOwner2.buyProperty(1);
        });
        assertEquals("Your balance: 0.00\nProperty price is: 20000000.00", thrown.getMessage());
    }

    @AfterAll
    static void clear() {
        propertyOwnerDatabase.deletePropertyFromDatabase("TestAddress");
        serviceDatabase.clearAllRequestedService();
        propertyOwnerDatabase.setStatusForProperty(1, 1, false);
        propertyOwnerDatabase.setUserIdForProperty(1, 1);
        propertyOwnerDatabase.setUserIdForProperty(2, 2);
        propertyOwnerDatabase.updateBalanceForUser(1, new BigDecimal("999999999.00"));
        propertyOwnerDatabase.updateBalanceForUser(1, new BigDecimal("0.00"));
    }
}