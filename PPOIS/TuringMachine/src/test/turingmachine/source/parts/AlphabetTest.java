package turingmachine.source.parts;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AlphabetTest {

    @Test
    void getBlankSymbol() {
        Assertions.assertEquals(' ', Alphabet.getBlankSymbol());
    }

    @Test
    void setBlankSymbol() {
        Alphabet.setBlankSymbol(' ');
        Assertions.assertEquals(' ' , Alphabet.getBlankSymbol());
    }
}