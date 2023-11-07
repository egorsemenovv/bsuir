package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthenticationTest {

    private static final Authentication authentication = new Authentication();
    @Test
    void logIn() {
        assertTrue(authentication.logIn("AdminAdminov", "12345Admin!"));
        assertFalse(authentication.logIn("AdminAdminov", "12345Admin"));
        assertFalse(authentication.logIn("NoSuchUser", "12345Admin!"));
    }

    @Test
    void signUp() {
        assertFalse(authentication.signUp("AdminAdminov", "12345Admin!", "admin@mail.com"));
        assertFalse(authentication.signUp("AdminAdminov", "12345Admin", "admin@mail.com"));
        assertTrue(authentication.signUp("Test123", "12345Test!", "test@email.com"));
        authentication.logIn("Test123", "12345Test!");
        DataBaseManager db = new DataBaseManager();
        db.deleteUser(authentication.person.getId());
    }
}