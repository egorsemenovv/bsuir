package org.example.worker;

import org.example.enums.ServiceStatus;
import org.example.service.Service;
import org.example.enums.Employee;
import java.math.BigDecimal;
import java.util.Optional;

public class Plumber extends Worker {
    private final Employee type = Employee.PLUMBER;
    private Optional<Service> currentOrder;

    public Plumber(int id, String username, String password, BigDecimal payment, BigDecimal balance) {
        super(id, username, password, payment, balance);
        currentOrder = workerDatabase.getWorkerOrder(id);
    }

    /**
     * takes first founded requested service for a worker
     *
     * @return true if successful, false if not
     */
    @Override
    public boolean startWork() {
        if (currentOrder.isPresent()) {
            return false;
        }
        Optional<Service> service = workerDatabase.takeWork(super.getId(), type);
        if (service.isPresent()) {
            System.out.println("Plumber started work");
            currentOrder = service;
            System.out.println(service.get());
            return true;
        } else {
            return false;
        }
    }

    /**
     * ends work, gets payment and close a request for service
     *
     * @return true if successful, false if not
     */
    @Override
    public boolean endWork() {
        if (currentOrder.isEmpty()) {
            return false;
        }
        workerDatabase.setStatusForWorker(super.getId(), false);
        serviceDatabase.setStatusAndWorkerIdForService(currentOrder.get().getId(), super.getId(), ServiceStatus.FINISHED);
        super.setBalance(super.getBalance().add(Employee.PLUMBER.getPayment()));
        workerDatabase.updateBalanceForWorker(super.getId(), super.getBalance());
        currentOrder = Optional.empty();
        return true;
    }
}
