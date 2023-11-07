package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordTest {

    @Test
    void isAppropriate() {
        assertTrue(Password.isAppropriate("12345Admin!"));
    }

    @Test
    void encode() {
        assertEquals("7mYpj+3BdUTQObfUFSDmDApohkNNLeyq0oXGJYHWyQ4=", Password.encode("12345Admin!"));
    }
}