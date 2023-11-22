package org.example.enums;

import org.example.user.Property;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BuildingTest {
    private static final List<Property> properties = new LinkedList<>();

    @BeforeAll
    static void setUp() {
        properties.add(new Property(1,
                1,
                150,
                1,
                "Moscow, Arbat Street 15, 1",
                "one-bedroom",
                new BigDecimal("20000000.00"),
                Building.APARTMENT));
    }

    @Test
    void findAll() {
        assertEquals(properties, Building.APARTMENT.findAll());
    }
}