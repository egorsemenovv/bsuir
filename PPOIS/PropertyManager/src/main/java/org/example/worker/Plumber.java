package org.example.worker;

import org.example.Service;
import org.example.database.DatabaseManager;

import java.math.BigDecimal;
import java.util.Optional;

public class Plumber extends Worker {
    private final Employee type = Employee.PLUMBER;
    private Optional<Service> currentOrder;

    public Plumber(int id, String username, String password, BigDecimal payment, BigDecimal balance) {
        super(id, username, password, payment, balance);
        currentOrder = db.getWorkerOrder(id);
    }

    @Override
    public boolean startWork() {
        if(currentOrder.isPresent()){
            return false;
        }
        Optional<Service> service = db.takeWork(super.getId(), type.getType());
        if(service.isPresent()){
            System.out.println("Plumber started work");
            currentOrder = service;
            System.out.println(service.get());
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean endWork() {
        if(currentOrder.isEmpty()){
            return false;
        }
        db.setStatusForWorker(super.getId(), false);
        db.setStatusAndWorkerIdForService(currentOrder.get().getId(), super.getId(),"FINISHED");
        super.setBalance(super.getBalance().add(Employee.PLUMBER.getPayment()));
        db.updateBalanceForWorker(super.getId(), super.getBalance());
        currentOrder = Optional.empty();
        return true;
    }
}
