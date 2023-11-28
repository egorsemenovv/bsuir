package org.example.service;

import org.example.database.DatabaseManager;
import org.example.database.ServiceDatabase;
import org.example.enums.Employee;
import org.example.enums.ServiceStatus;
import org.example.worker.Worker;

public class Service {
    private final ServiceDatabase serviceDatabase = new ServiceDatabase();
    private long id;
    private int propertyId;
    private Employee employee;
    private int workerId;
    private ServiceStatus status;

    public Service(long id, int propertyId, Employee employee, int workerId, ServiceStatus status) {
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
        return serviceDatabase.getAddressByPropertyId(this.propertyId);
    }
}
