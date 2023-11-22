package org.example.worker;

import org.example.database.DatabaseManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlumberTest {
    private static final Worker workerPlumber = new Plumber(2,
            "Anatoliy Pavlovich",
            "C13HlZNn3j2hs/FtYtlYggdP7zPmHU+XAflKwU/jUPU=",
            new BigDecimal("100.00"),
            new BigDecimal("0.00"));
    private static final DatabaseManager db = new DatabaseManager();

    @BeforeAll
    static void startUp(){
        db.addRequestForService(1, Employee.ELECTRICIAN);
        db.addRequestForService(2, Employee.PLUMBER);
    }
    @Test
    void startWork() {
        assertTrue(workerPlumber.startWork());
        assertFalse(workerPlumber.startWork());
    }

    @Test
    void endWork() {
        assertTrue(workerPlumber.endWork());
        assertFalse(workerPlumber.endWork());
    }

    @AfterAll
    static void clear(){
        db.updateBalanceForWorker(2, new BigDecimal("0.00"));
    }
}
