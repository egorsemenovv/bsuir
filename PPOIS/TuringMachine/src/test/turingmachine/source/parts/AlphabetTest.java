package turingmachine.source.parts;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


class AlphabetTest {

    @BeforeAll
    public static void setUp(){
        Alphabet.setAlphabet("0123456789");
    }

    @Test
    void getBlankSymbol() {
        Assertions.assertEquals(' ', Alphabet.getBlankSymbol());
    }

    @Test
    void isNormalTape(){
        Assertions.assertTrue(Alphabet.isNormalTape("10101"));
        Assertions.assertFalse(Alphabet.isNormalTape("abcde"));
    }

    @Test
    void getAlphabet(){
        Assertions.assertEquals("10101", Alphabet.getAlphabet());
    }

    @Test
    void setAlphabet(){
        Alphabet.setAlphabet("10101");
        Assertions.assertEquals("10101", Alphabet.getAlphabet());
    }
}