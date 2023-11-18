package org.example;

import java.math.BigDecimal;
import java.util.List;

public class Main {
    public static void main(String[] args) {
       DatabaseManager db = new DatabaseManager();
       db.addProperty(1, 125, 1, "Moscow, Arbat Street 01", "one-bedroom in cetre of city", new BigDecimal("200000.00"), Building.APARTMENT);
        List<Property> properties = Building.APARTMENT.findAll();
        for (Property property:
             properties) {
            System.out.println(property);
        }

    }


}
