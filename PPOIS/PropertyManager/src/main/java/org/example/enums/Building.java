package org.example.enums;

import org.example.database.UserDatabase;
import org.example.user.Property;

import java.util.List;

public enum Building {
    APARTMENT,
    HOUSE,
    VILLA,
    MANSION,
    COTTAGE;
    private final UserDatabase userDatabase = new UserDatabase();

    /**
     * finds all buildings with such type
     *
     * @return list of properties
     */
    public List<Property> findAll() {
        return userDatabase.getPropertiesByType(Building.this);
    }

}
