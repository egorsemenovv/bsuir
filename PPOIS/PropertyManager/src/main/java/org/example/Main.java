package org.example;

import org.example.database.DatabaseManager;
import org.example.user.User;
import org.example.worker.*;

import java.math.BigDecimal;


public class Main {
    public static void main(String[] args) {
       DatabaseManager db = new DatabaseManager();
        User user = new User(2,
                "User Test",
                "Cyi6fm29flLpHIp86JVMuvgAmAmriPxJ1G6YUCKzFuY=",
                "+375987654321",
                "test@mail.com",
                new BigDecimal("1000.00"));
        Worker worker = new Electrician(1,"Pavel Anatolievich", "VnCGVr8gU+kMiHgvjDtsrgCV9LBvBwzQzNSJd5EV8lk=",
                Employee.ELECTRICIAN.getPayment(), new BigDecimal("0.00"));
        worker.endWork();
    }


}
