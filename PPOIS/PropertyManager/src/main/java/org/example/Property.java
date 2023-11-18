package org.example;

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
    public String toString() {
        return "Property{" +
                "id=" + id +
                ", personId=" + personId +
                ", square=" + square +
                ", floors=" + floors +
                ", address='" + address + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", type=" + type +
                '}';
    }
}
