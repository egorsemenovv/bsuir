package org.example.user;

import org.example.utils.Password;
import org.example.database.DatabaseManager;

import java.util.Optional;

public class Authentication {
    public User user;
    private static final DatabaseManager db = new DatabaseManager();

    /**
     * to log in into your account
     * @param username person`s username
     * @param password person`s password
     * @return true if successful, false if not
     */
    public boolean logIn(String username, String password) {
        if(!Password.isAppropriate(password)){
            return false;
        }
        password = Password.encode(password);
        Optional<User> opt = db.getUserFromDatabase(username, password);
        if(opt.isPresent()) {
            this.user = opt.get();
            return true;
        }
        return false;
    }

    /**
     * creates new user
     * @param username person`s username
     * @param password person`s password
     * @param email person`s email
     * @return true if successful, false if not
     */
    public boolean signUp(String username,String password, String phoneNumber, String email) {
        if(!Password.isAppropriate(password)){
            return false;
        }
        return db.addUserToDatabase(username, Password.encode(password), phoneNumber, email);
    }
}
