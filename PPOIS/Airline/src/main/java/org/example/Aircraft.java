package org.example;

public class Aircraft {
    private final int id;
    private final String model;

    /**
     * @param id aircraft id
     * @param model model of aircraft
     */
    public Aircraft(int id, String model) {
        this.id = id;
        this.model = model;
    }

    /**
     * @return aircraft id
     */
    public int getId() {
        return id;
    }

    /**
     * @return aircraft model
     */
    public String getModel() {
        return model;
    }

}
