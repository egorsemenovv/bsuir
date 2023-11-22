package org.example.worker;

import org.example.database.DatabaseManager;
import org.example.enums.Employee;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthenticationTest {
    private static final Authentication authentication = new Authentication();
    @Test
    void logIn() {
        assertTrue(authentication.logIn("Pavel Anatolievich", "12345electrician!"));
        assertTrue(authentication.logIn("Anatoliy Pavlovich", "12345plumber!"));
        assertFalse(authentication.logIn("Pavel Anatolievich", "12345electrician"));
        assertFalse(authentication.logIn("NoSuchUser", "12345electrician!"));
    }

    @Test
    void signUp() {
        assertFalse(authentication.signUp("Test", "12345user", Employee.ELECTRICIAN));
        assertFalse(authentication.signUp("Pavel Anatolievich", "12345user", Employee.ELECTRICIAN));
        assertTrue(authentication.signUp("Test", "12345user!", Employee.ELECTRICIAN));
        DatabaseManager db = new DatabaseManager();
        db.deleteWorkerByUsername("Test");
    }
}