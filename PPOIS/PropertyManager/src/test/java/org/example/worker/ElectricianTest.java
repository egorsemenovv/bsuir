package org.example.worker;

import org.example.database.ServiceDatabase;
import org.example.database.WorkerDatabase;
import org.example.enums.Employee;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ElectricianTest {
    private static final Worker workerElectrician = new Electrician(1,
            "Pavel Anatolievich",
            "C13HlZNn3j2hs/FtYtlYggdP7zPmHU+XAflKwU/jUPU=",
            new BigDecimal("150.00"),
            new BigDecimal("0.00"));
    private static final WorkerDatabase workerDatabase = new WorkerDatabase();
    private static final ServiceDatabase serviceDatabase = new ServiceDatabase();

    @BeforeAll
    static void startUp() {
        serviceDatabase.addRequestForService(1, Employee.ELECTRICIAN);
    }

    @Test
    void startWork() {
        assertTrue(workerElectrician.startWork());
        assertFalse(workerElectrician.startWork());
    }

    @Test
    void endWork() {
        assertTrue(workerElectrician.endWork());
        assertFalse(workerElectrician.endWork());
    }

    @AfterAll
    static void clear() {
        workerDatabase.updateBalanceForWorker(1, new BigDecimal("0.00"));
    }
}