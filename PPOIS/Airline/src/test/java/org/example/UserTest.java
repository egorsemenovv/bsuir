package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private static final User user = new User(3, "FirstUser", "nHgamgG8rRcDgTAroRYpoa8soPhzSxrLQ6qIiIz0NWo=", "firstUser@gmail.com");

    @Test
    void buyCancelTicket() {
        assertTrue(user.buyTicket("Minsk", "Moscow", 1, "A1"));
        assertFalse(user.cancelTicket(0));
        assertTrue(user.cancelTicket(1));
        assertFalse(user.cancelTicket(1));
        assertFalse(user.buyTicket("NoCity", "NoCity", 1, "A1"));
        assertFalse(user.buyTicket("Minsk", "Moscow", 0, "A1"));
        assertFalse(user.buyTicket("Minsk", "Moscow", 1, "NN"));
    }

}