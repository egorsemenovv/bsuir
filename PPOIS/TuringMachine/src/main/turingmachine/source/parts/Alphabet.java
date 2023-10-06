package turingmachine.source.parts;


public class Alphabet {
    /**
     * blank symbol on tape
     */
    private static char blankSymbol = ' ';

    /**
     *
     * @return blank symbol from alphabet
     */
    public static char getBlankSymbol() {
        return blankSymbol;
    }

    /**
     *
     * @param nullSymbol symbol to set new blank symbol for alphabet
     */
    public static void setBlankSymbol(char nullSymbol){
        Alphabet.blankSymbol = nullSymbol;}

}


