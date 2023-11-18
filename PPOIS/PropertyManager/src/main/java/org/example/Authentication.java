package org.example;

import java.util.Optional;
import java.util.Scanner;
import java.util.function.Consumer;

public class Authentication {
    public static User user;
    private static final DatabaseManager db = new DatabaseManager();

    /**
     * to log in into your account
     * @param username person`s username
     * @param password person`s password
     * @return true if successful, false if not
     */
    public static boolean logIn(String username,String password) {
        Optional<User> opt = db.getUserFromDatabase(username, password);
        if(opt.isPresent()) {
            user = opt.get();
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
        return db.addUser(username, Password.encode(password), phoneNumber, email);
    }
}
