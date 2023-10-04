package turingmachine.source.parts;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class TransitionTest {

    private static Transition transition;

    @BeforeAll
    public static void setUp(){
        transition = new Transition("q10R");
    }

    @Test
    void getTransitionalState() {
        Assertions.assertEquals('0', transition.getWriteableSymbol());
    }

    @Test
    void getWriteableSymbol() {
        Assertions.assertEquals(1, transition.getTransitionalState());
    }

    @Test
    void getShift() {
        Assertions.assertEquals('R', transition.getShift());
    }
}