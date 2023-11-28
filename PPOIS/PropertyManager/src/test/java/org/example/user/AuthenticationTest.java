package org.example.user;

import org.example.database.UserDatabase;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthenticationTest {
    private static final Authentication authentication = new Authentication();

    @Test
    void logIn() {
        assertTrue(authentication.logIn("Test User", "12345user!"));
        assertFalse(authentication.logIn("Test User", "12345user"));
        assertFalse(authentication.logIn("NoSuchUser", "12345user!"));
    }

    @Test
    void signUp() {
        assertFalse(authentication.signUp("Test", "12345user", "+375", "usertest@mail.com"));
        assertFalse(authentication.signUp("User Test", "12345user", "+375", "usertest@mail.com"));
        assertTrue(authentication.signUp("Test", "12345user!", "+375", "usertest@mail.com"));
        UserDatabase userDatabase = new UserDatabase();
        userDatabase.deleteUserByUsername("Test");
    }
}