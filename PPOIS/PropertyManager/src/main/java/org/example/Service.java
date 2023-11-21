package org.example;

import org.example.database.DatabaseManager;
import org.example.worker.Employee;

public class Service {
    private final DatabaseManager db = new DatabaseManager();
    private long id;
    private int propertyId;
    private org.example.worker.Employee employee;
    private int workerId;
    private String status;

    public Service(long id, int propertyId, Employee employee, int workerId, String status) {
        this.id = id;
        this.propertyId = propertyId;
        this.employee = employee;
        this.workerId = workerId;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return db.getAddressByPropertyId(this.propertyId);
    }
}
