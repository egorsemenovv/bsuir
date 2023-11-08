package org.example;

import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdminTest {

    private static final Admin admin = new Admin(1, "AdminAdminov", "7mYpj+3BdUTQObfUFSDmDApohkNNLeyq0oXGJYHWyQ4=", "admin@mail.com");

    @Test
    void makeUserAdmin() {
        assertTrue(admin.makeUserAdmin(2));
    }

    @Test
    void addDeleteFlight() {
        assertTrue(admin.addFlight("TST001",
                "Jan 30 12:00:00 2025",
                "Jan 31 12:00:00 2025",
                "MSK",
                "MNK",
                "SCHEDULED",
                1));
        assertTrue(admin.deleteFlight("TST001"));
        assertFalse(admin.addFlight("TST001",
                "Jan 35 12:00:00 2025",
                "Jan 31 12:00:00 2025",
                "MSK",
                "MNK",
                "SCHEDULED",
                1));
        assertFalse(admin.addFlight("TST001",
                "Jan 30 12:00:00 2025",
                "Jan 35 12:00:00 2025",
                "MSK",
                "MNK",
                "SCHEDULED",
                1));
        assertFalse(admin.addFlight("TST001",
                "Jan 30 12:00:00 2025",
                "Jan 31 12:00:00 2025",
                "MEK",
                "MNK",
                "SCHEDULED",
                1));
        assertFalse(admin.addFlight("TST001",
                "Jan 30 12:00:00 2025",
                "Jan 31 12:00:00 2025",
                "MSK",
                "MEK",
                "SCHEDULED",
                1));
        assertFalse(admin.addFlight("TST001",
                "Jan 30 12:00:00 2025",
                "Jan 31 12:00:00 2025",
                "MSK",
                "MNK",
                "SCHEDULED",
                0));
    }

}