package org.example;

import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AirportTest {

    private static Airport airport = new Airport("MSK", "Russia", "Moscow");
    @Test
    void getCode() {
        assertEquals("MSK", airport.getCode());
    }

    @Test
    void getCountry() {
        assertEquals("Russia", airport.getCountry());
    }

    @Test
    void getCity() {
        assertEquals("Moscow", airport.getCity());
    }
}