package org.example.worker;

import org.example.Password;
import org.example.database.DatabaseManager;

import java.util.Optional;

public class Authentication {
    public static Worker worker;
    private static final DatabaseManager db = new DatabaseManager();

    /**
     * to log in into your account
     * @param username person`s username
     * @param password person`s password
     * @return true if successful, false if not
     */
    public static boolean logIn(String username,String password) {
        Optional<Worker> opt = db.getWorkerFromDatabase(username, password);
        if(opt.isPresent()) {
            worker = opt.get();
            return true;
        }
        return false;
    }

    /**
     * creates new user
     * @param username worker`s username
     * @param password worker`s password
     * @param payment worker`s payment
     * @return true if successful, false if not
     */
    public boolean signUp(String username,String password, Employee employee) {
        return db.addWorkerToDatabase(username, Password.encode(password), employee);
    }
}
