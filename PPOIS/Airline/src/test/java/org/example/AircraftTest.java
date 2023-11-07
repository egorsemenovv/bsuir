package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AircraftTest {
    private static final Aircraft aircraft = new Aircraft(1, "Airbus A350");

    @Test
    void getId() {
        assertEquals(1, aircraft.getId());
    }

    @Test
    void getModel() {
        assertEquals("Airbus A350", aircraft.getModel());
    }
}