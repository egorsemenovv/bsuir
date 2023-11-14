package org.example;

public enum Building {
    APARTMENT("Apartment"),
    HOUSE("House"),
    VILLA("Villa"),
    MANSION("Mansion"),
    COTTAGE("Cottage");

    private final String title;

    Building(String title){
        this.title=title;
    }

    public String getTitle(){
        return title;
    }
}
