package org.example.propertyowner;

import org.example.enums.Building;

import java.util.Objects;

public class Property {
    private int id;
    private int personId;
    private int square;
    private int floors;
    private String address;
    private String description;
    private java.math.BigDecimal price;
    private Building type;

    public Property(int id, int personId, int square, int floors, String address, String description, java.math.BigDecimal price, Building type) {
        this.id = id;
        this.personId = personId;
        this.square = square;
        this.floors = floors;
        this.address = address;
        this.description = description;
        this.price = price;
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Property property = (Property) o;
        return id == property.id && personId == property.personId && square == property.square && floors == property.floors && Objects.equals(address, property.address) && Objects.equals(description, property.description) && Objects.equals(price, property.price) && type == property.type;
    }
}
