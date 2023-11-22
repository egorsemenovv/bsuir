package org.example.service;

import org.example.database.DatabaseManager;
import org.example.enums.Employee;

public class Service {
    private final DatabaseManager db = new DatabaseManager();
    private long id;
    private int propertyId;
    private Employee employee;
    private int workerId;
    private String status;

    public Service(long id, int propertyId, Employee employee, int workerId, String status) {
        this.id = id;
        this.propertyId = propertyId;
        this.employee = employee;
        this.workerId = workerId;
        this.status = status;
    }

    /**
     * @return id of property
     */
    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return db.getAddressByPropertyId(this.propertyId);
    }
}
