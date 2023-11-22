package org.example.worker;

import org.example.service.Service;
import org.example.enums.Employee;

import java.math.BigDecimal;
import java.util.Optional;

public class Electrician extends Worker {

    private final Employee type = Employee.ELECTRICIAN;

    private Optional<Service> currentOrder;

    public Electrician(int id, String username, String password, BigDecimal payment, BigDecimal balance) {
        super(id, username, password, payment, balance);
        currentOrder = db.getWorkerOrder(id);
    }

    /**
     * takes first founded requested service for a worker
     *
     * @return true if successful, false if not
     */
    @Override
    public boolean startWork() {
        if(currentOrder.isPresent()){
            return false;
        }
        Optional<Service> service = db.takeWork(super.getId(), type.getType());
        if(service.isPresent()){
            System.out.println("Electrician started work");
            currentOrder = service;
            System.out.println(service.get());
            return true;
        }else {
            return false;
        }
    }

    /**
     * ends work, gets payment and close a request for service
     * @return true if successful, false if not
     */
    @Override
    public boolean endWork() {
        if(currentOrder.isEmpty()){
            return false;
        }
        db.setStatusForWorker(super.getId(), false);
        db.setStatusAndWorkerIdForService(currentOrder.get().getId(), super.getId(),"FINISHED");
        super.setBalance(super.getBalance().add(Employee.ELECTRICIAN.getPayment()));
        db.updateBalanceForWorker(super.getId(), super.getBalance());
        currentOrder = Optional.empty();
        return true;
    }
}
