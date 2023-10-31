package org.example;

public class Aircraft {
    private final int id;
    private final String model;

    public Aircraft(int id, String model) {
        this.id = id;
        this.model = model;
    }

    public int getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

}
