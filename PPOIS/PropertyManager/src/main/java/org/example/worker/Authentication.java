package org.example.worker;

import org.example.Password;
import org.example.database.DatabaseManager;

import java.util.Optional;

public class Authentication {
    public Worker worker;
    private final DatabaseManager db = new DatabaseManager();

    /**
     * to log in into your account
     * @param username person`s username
     * @param password person`s password
     * @return true if successful, false if not
     */
    public boolean logIn(String username,String password) {
        password= Password.encode(password);
        Optional<Worker> opt = db.getWorkerFromDatabase(username, password);
        if(opt.isPresent()) {
            this.worker = opt.get();
            return true;
        }
        return false;
    }

    /**
     * creates new user
     * @param username worker`s username
     * @param password worker`s password
     * @param employee worker`s position
     * @return true if successful, false if not
     */
    public boolean signUp(String username,String password, Employee employee) {
        if (!Password.isAppropriate(password)) {
            return false;
        }
        return db.addWorkerToDatabase(username, Password.encode(password), employee);
    }
}
