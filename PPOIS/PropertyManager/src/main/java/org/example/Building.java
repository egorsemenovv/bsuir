package org.example;

import java.util.List;

public enum Building {
    APARTMENT("APARTMENT"),
    HOUSE("HOUSE"),
    VILLA("VILLA"),
    MANSION("MANSION"),
    COTTAGE("COTTAGE");
    private final DatabaseManager db = new DatabaseManager();
    private final String type;

    Building(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public List<Property> findAll(){
        return db.getPropertiesByType(Building.this);
    }

}
