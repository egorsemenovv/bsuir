package org.example.enums;

import org.example.database.PropertyOwnerDatabase;
import org.example.propertyowner.Property;

import java.util.List;

public enum Building {
    APARTMENT,
    HOUSE,
    VILLA,
    MANSION,
    COTTAGE;
    private final PropertyOwnerDatabase propertyOwnerDatabase = new PropertyOwnerDatabase();

    /**
     * finds all buildings with such type
     *
     * @return list of properties
     */
    public List<Property> findAll() {
        return propertyOwnerDatabase.getPropertiesByType(Building.this);
    }

}
