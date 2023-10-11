package turingmachine.source.parts;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Alphabet {

    private static String alphabet;
    private static char blankSymbol = ' ';

    /**
     * @return blank symbol from alphabet
     */
    public static char getBlankSymbol() {
        return blankSymbol;
    }

    /**
     * @param nullSymbol symbol to set new blank symbol for alphabet
     */
    public static void setBlankSymbol(char nullSymbol) {
        Alphabet.blankSymbol = nullSymbol;
    }

    /**
     * checks if tape consists of alphabet
     *
     * @return true if yes, false if no
     */
    public static boolean isNormalTape(String inputTape) {
        Pattern pattern = Pattern.compile('[' + Alphabet.getAlphabet() + "]+");
        Matcher matcher = pattern.matcher(inputTape);
        return matcher.matches();
    }

    /**
     * @return alphabet string
     */
    public static String getAlphabet() {
        return alphabet;
    }

    /**
     * @param alphabet sets alphabet for Turing Machine
     */
    public static void setAlphabet(String alphabet) {
        Alphabet.alphabet = alphabet;
    }

}


