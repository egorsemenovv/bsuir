package org.example.worker;

import org.example.database.DatabaseManager;
import org.example.database.ServiceDatabase;
import org.example.database.WorkerDatabase;
import org.example.enums.Employee;
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
    private static final WorkerDatabase workerDatabase = new WorkerDatabase();
    private static final ServiceDatabase serviceDatabase = new ServiceDatabase();

    @BeforeAll
    static void startUp() {
        serviceDatabase.addRequestForService(2, Employee.PLUMBER);
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
    static void clear() {
        workerDatabase.updateBalanceForWorker(2, new BigDecimal("0.00"));
    }
}
