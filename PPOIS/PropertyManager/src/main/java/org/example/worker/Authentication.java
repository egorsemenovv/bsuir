package org.example.worker;

import org.example.database.WorkerDatabase;
import org.example.utils.Password;
import org.example.enums.Employee;

import java.util.Optional;

public class Authentication {
    public Worker worker;
    private final WorkerDatabase workerDatabase = new WorkerDatabase();

    /**
     * to log in into your account
     *
     * @param username worker`s username
     * @param password worker`s password
     * @return true if successful, false if not
     */
    public boolean logIn(String username, String password) {
        password = Password.encode(password);
        Optional<Worker> opt = workerDatabase.getWorkerFromDatabase(username, password);
        if (opt.isPresent()) {
            this.worker = opt.get();
            return true;
        }
        return false;
    }

    /**
     * creates new user
     *
     * @param username worker`s username
     * @param password worker`s password
     * @param employee worker`s position
     * @return true if successful, false if not
     */
    public boolean signUp(String username, String password, Employee employee) {
        if (!Password.isAppropriate(password)) {
            return false;
        }
        return workerDatabase.addWorkerToDatabase(username, Password.encode(password), employee);
    }
}
