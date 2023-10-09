package turingmachine.source.parts;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


class CarriageTest {

    private static final Carriage carriage = new Carriage();
    private static final Tape tape = new Tape();
    private static final RuleSet ruleSet = new RuleSet();

    @BeforeAll
    public static void setUp(){
        carriage.setCurrentIndex(0);
        carriage.setFinalState(1);
        carriage.setCurrentState(1);
        tape.setTape("1");
        ruleSet.addRule("q01->q10R");
    }

    @Test
    void getFinalState() {
        Assertions.assertEquals(1, carriage.getFinalState());
    }

    @Test
    void setCurrentIndex() {
        Carriage temp = new Carriage();
        temp.setCurrentIndex(1);
        Assertions.assertEquals(1, temp.getCurrentIndex());
    }

    @Test
    void setFinalState() {
        carriage.setFinalState(1);
        Assertions.assertEquals(1, carriage.getFinalState());
    }

    @Test
    void move() {
        Carriage temp = new Carriage();
        temp.setFinalState(9);
        temp.move(tape, ruleSet);
        Assertions.assertEquals("0 ", tape.getTape().toString());
        tape.setTape("1");
        ruleSet.delRule("q01->q10R");
        temp.setCurrentIndex(0);
        ruleSet.addRule("q11->q21L");
        temp.move(tape, ruleSet);
        Assertions.assertEquals(" 1", tape.getTape().toString());
        tape.setTape("0");
        ruleSet.delRule("q11->q21L");
        temp.setCurrentIndex(0);
        temp.move(tape, ruleSet);
        Assertions.assertEquals("0", tape.getTape().toString());
    }

    @Test
    void setCurrentState() {
        Carriage temp = new Carriage();
        temp.setCurrentState(10);
        Assertions.assertEquals(10 , temp.getCurrentState());
    }

    @Test
    void getCurrentState() {
        Assertions.assertEquals(1, carriage.getCurrentState());
    }

    @Test
    void isOnFinalState() {
        Carriage temp = new Carriage();
        temp.setFinalState(0);
        Assertions.assertTrue(temp.isOnFinalState());
    }
}