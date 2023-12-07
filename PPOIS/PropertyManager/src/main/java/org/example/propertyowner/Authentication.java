package org.example.propertyowner;

import org.example.database.PropertyOwnerDatabase;
import org.example.utils.Password;

import java.util.Optional;

public class Authentication {
    public PropertyOwner propertyOwner;
    private static final PropertyOwnerDatabase PROPERTY_OWNER_DATABASE = new PropertyOwnerDatabase();

    /**
     * to log in into your account
     *
     * @param username person`s username
     * @param password person`s password
     * @return true if successful, false if not
     */
    public boolean logIn(String username, String password) {
        if (!Password.isAppropriate(password)) {
            return false;
        }
        password = Password.encode(password);
        Optional<PropertyOwner> opt = PROPERTY_OWNER_DATABASE.getUserFromDatabase(username, password);
        if (opt.isPresent()) {
            this.propertyOwner = opt.get();
            return true;
        }
        return false;
    }

    /**
     * creates new user
     *
     * @param username person`s username
     * @param password person`s password
     * @param email    person`s email
     * @return true if successful, false if not
     */
    public boolean signUp(String username, String password, String phoneNumber, String email) {
        if (!Password.isAppropriate(password)) {
            return false;
        }
        return PROPERTY_OWNER_DATABASE.addUserToDatabase(username, Password.encode(password), phoneNumber, email);
    }
}
