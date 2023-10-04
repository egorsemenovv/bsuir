package turingmachine.source.parts;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class TapeTest {


    public static final Tape tape =  new Tape();

    @BeforeAll
    public static void setUp(){
        tape.setTape("*111x11=*");
    }

    @Test
    void getLength() {
        Assertions.assertEquals(9, tape.getLength());
    }

    @Test
    void getTape() {
        Assertions.assertEquals("*111x11=*", tape.getTape().toString());
    }

    @Test
    void addLeft() {
        Tape temp = new Tape();
        Assertions.assertEquals(Alphabet.getBlankSymbol(), temp.addLeft());
    }

    @Test
    void addRight() {
        Tape temp = new Tape();
        Assertions.assertEquals(Alphabet.getBlankSymbol(), temp.addRight());
    }

    @Test
    void getInputSymbol() {
        Assertions.assertEquals('x', tape.getInputSymbol(4));
    }

}